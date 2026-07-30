package com.IcyDrae.Controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import com.IcyDrae.Services.PrayerNotificationService;
import com.IcyDrae.Services.PrayerNotificationListener;
import javafx.application.Platform;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MainController {
    @FXML
    private HBox notificationCard;
    @FXML
    private StackPane mainContent;
    @FXML
    private Button prayerButton;
    @FXML
    private Button tomorrowButton;
    @FXML
    private Button dhikrButton;
    @FXML
    private Button quranButton;
    @FXML
    private Button hijriButton;
    @FXML
    private Button fastingButton;
    @FXML
    private Button namesButton;
    private PrayerNotificationService notificationService;
    // Notification card
    @FXML
    private Label notificationPrayer;
    @FXML
    private Label notificationTime;
    @FXML
    private Label countdownLabel;
    private Timeline countdownTimeline;
    private LocalDateTime notificationMoment;
    private String activePage = "Prayer";

    @FXML
    public void initialize() throws Exception {
        loadPage("/Views/Prayer.fxml");
        setActive("Prayer");

        notificationService = new PrayerNotificationService();
        notificationService.setListener(
            new PrayerNotificationListener() {
                @Override
                public void onNextPrayerScheduled(String prayerName, String time) {
                    Platform.runLater(() -> {
                        notificationPrayer.setText("📿 " + prayerName);
                        notificationTime.setText("Adhan will play at " + time);
                    });
                }

                @Override
                public void onCountdownUpdate(String remaining) {
                    Platform.runLater(() -> {
                        countdownLabel.setText(remaining);
                    });
                }

                @Override
                public void onAdhanPlayed(String prayerName) {
                    Platform.runLater(() -> {
                        notificationPrayer.setText("🕌 Playing Adhan for " + prayerName);
                        notificationTime.setText("");
                        countdownLabel.setText("00:00:00");
                    });
                }
            }
        );
        notificationService.start();
    }

    /*
    Called by PrayerNotificationService later.
    */
    public void showNextNotification(String prayerName, LocalDateTime notifyTime) {
        this.notificationMoment = notifyTime;
        notificationPrayer.setText("📿 " + prayerName);
        notificationTime.setText(
            "Adhan will play at "
                + String.format(
                "%02d:%02d",
                notifyTime.getHour(),
                notifyTime.getMinute()
            )
        );
        startCountdown();
    }

    private void startCountdown() {
        if (countdownTimeline != null) {
            countdownTimeline.stop();
        }
        countdownTimeline = new Timeline(
            new KeyFrame(
                Duration.seconds(1),
                event -> updateCountdown()
            )
        );
        countdownTimeline.setCycleCount(
            Animation.INDEFINITE
        );
        countdownTimeline.play();
        updateCountdown();
    }

    private void updateCountdown() {
        if (notificationMoment == null) {
            return;
        }

        long seconds = ChronoUnit.SECONDS.between(
            LocalDateTime.now(),
            notificationMoment
        );

        if (seconds <= 0) {
            countdownLabel.setText("00:00:00");

            return;
        }

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        countdownLabel.setText(
            String.format(
                "%02d:%02d:%02d",
                hours,
                minutes,
                secs
            )
        );
    }

    private void loadPage(String fxml) throws Exception {
        Parent view = FXMLLoader.load(
            getClass().getResource(fxml)
        );

        mainContent.getChildren().setAll(view);
    }

    private void setActive(String page) {
        activePage = page;

        updateMenuStyles();

        updateNotificationVisibility();
    }

    private void updateMenuStyles() {
        reset(prayerButton);
        reset(tomorrowButton);
        reset(dhikrButton);
        reset(quranButton);
        reset(hijriButton);
        reset(fastingButton);
        reset(namesButton);

        switch (activePage) {
            case "Prayer" -> activate(prayerButton);
            case "Tomorrow" -> activate(tomorrowButton);
            case "Dhikr" -> activate(dhikrButton);
            case "Quran" -> activate(quranButton);
            case "Hijri" -> activate(hijriButton);
            case "Fasting" -> activate(fastingButton);
            case "Names" -> activate(namesButton);
        }
    }

    private void activate(Button button) {
        button.setDisable(true);
        button.setStyle("""
            -fx-background-color:#C9A24A;
            -fx-text-fill:black;
            -fx-font-weight:bold;
            -fx-font-size:16px;
            """);
    }

    private void reset(Button button) {
        button.setDisable(false);
        button.setStyle("""
            -fx-background-color:transparent;
            -fx-text-fill:#E8E6E1;
            -fx-font-size:16px;
            """);
    }

    @FXML
    private void prayerClicked() throws Exception {
        loadPage("/Views/Prayer.fxml");
        setActive("Prayer");
    }

    @FXML
    private void tomorrowClicked() throws Exception {
        loadPage("/Views/Tomorrow.fxml");
        setActive("Tomorrow");
    }

    @FXML
    private void dhikrClicked() throws Exception {
        loadPage("/Views/Dhikr.fxml");
        setActive("Dhikr");
    }

    @FXML
    private void quranClicked() throws Exception {
        loadPage("/Views/Quran.fxml");
        setActive("Quran");
    }

    @FXML
    private void hijriClicked() throws Exception {
        loadPage("/Views/Hijri.fxml");
        setActive("Hijri");
    }

    @FXML
    private void fastingClicked() throws Exception {
        loadPage("/Views/Fasting.fxml");
        setActive("Fasting");
    }

    @FXML
    private void namesClicked() throws Exception {
        loadPage("/Views/Names.fxml");
        setActive("Names");
    }

    private void updateNotificationVisibility() {
        boolean visible = activePage.equals("Prayer");

        notificationCard.setVisible(visible);
        notificationCard.setManaged(visible);
    }
}
