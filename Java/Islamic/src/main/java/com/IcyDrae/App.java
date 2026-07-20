package com.IcyDrae;

import com.IcyDrae.Data.NextPrayer;
import com.IcyDrae.Data.PrayerTimesResponse;
import com.IcyDrae.Services.LocationService;
import com.IcyDrae.Services.PrayerTimesService;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        PrayerTimesService PrayerTimesService = new PrayerTimesService();

        System.out.println("Prayer times of tomorrow: " +
            PrayerTimesService.fetchTomorrowPrayerTimes().getData().getTimings().getFajr()
        );
    }
}
