package com.IcyDrae;

import com.IcyDrae.Services.LocationService;
import com.IcyDrae.Services.PrayerTimesService;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        PrayerTimesService PrayerTimesService = new PrayerTimesService();
        System.out.println("Prayer times: " +
            PrayerTimesService
                .fetchAllPrayerTimesForToday()
                .getData()
                .getTimings().getMidnight()
        );
    }
}
