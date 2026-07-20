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
        PrayerTimesResponse PrayersOfToday = PrayerTimesService.fetchAllPrayerTimesForToday();

        NextPrayer NextPrayer = PrayerTimesService.fetchNextPrayerTime(
            PrayersOfToday
            .getData()
            .getTimings()
        );

        System.out.println("Next prayer: " +
            NextPrayer.getName()
            + " at: " +
            NextPrayer.getTime()
        );
    }
}
