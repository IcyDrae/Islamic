package com.IcyDrae.Services;

public interface PrayerNotificationListener {
    void onNextPrayerScheduled(String prayerName, String time);
    void onCountdownUpdate(String remaining);
    void onAdhanPlayed(String prayerName);
}
