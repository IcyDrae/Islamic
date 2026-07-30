package com.IcyDrae.Services;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrayerNotificationService {
    private boolean running = false;
    private Thread thread;
    private PrayerNotificationListener listener;

    public void setListener(PrayerNotificationListener listener) {
        this.listener = listener;
    }

    public void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(() -> {
            while (running) {
                try {
                    waitForNextPrayer();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    private void waitForNextPrayer() throws Exception {
        PrayerTimesService service = new PrayerTimesService();

        var nextPrayer = service.fetchNext(
            service.fetchForToday().getData().getTimings()
        );

        LocalDate today = LocalDate.now();
        LocalDateTime prayerTime = LocalDateTime.of(
            today,
            nextPrayer.getTime()
        );

        /*
         * If today's prayer has already passed,
         * assume it is tomorrow.
         */
        if (prayerTime.isBefore(LocalDateTime.now())) {
            prayerTime = prayerTime.plusDays(1);
        }

        LocalDateTime notifyTime = prayerTime.minusMinutes(10);

        if (listener != null) {
            listener.onNextPrayerScheduled(
                nextPrayer.getName(),
                notifyTime.toLocalTime().toString()
            );
        }

        while (running) {
            LocalDateTime now = LocalDateTime.now();

            Duration remaining = Duration.between(now, notifyTime);

            if (!remaining.isNegative()) {
                long hours = remaining.toHours();
                long minutes = remaining.toMinutes() % 60;
                long seconds = remaining.getSeconds() % 60;

                String countdown = String.format(
                    "%02d:%02d:%02d",
                    hours,
                    minutes,
                    seconds
                );

                if (listener != null) {
                    listener.onCountdownUpdate(countdown);
                }

                Thread.sleep(1000);
            } else {
                break;
            }
        }

        playAdhan();

        if (listener != null) {
            listener.onAdhanPlayed(nextPrayer.getName());
        }

        /*
         * Wait a minute before looking
         * for the next prayer.
         */
        Thread.sleep(60_000);
    }

    private void playAdhan() {
        try {
            InputStream audio = getClass().getResourceAsStream("/adhan.mp3");

            if (audio == null) {
                System.out.println("adhan.mp3 not found");

                return;
            }

            BufferedInputStream buffered = new BufferedInputStream(audio);
            AudioInputStream ais = AudioSystem.getAudioInputStream(buffered);

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
