package com.IcyDrae.Services;

import com.IcyDrae.Data.FastingDays;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.util.Arrays;

public class FastingDaysService {
    public FastingDays getFastingInfo() {
        LocalDate today = LocalDate.now();

        HijrahDate hijri = HijrahDate.from(today);

        int hijriDay = hijri.get(
            java.time.temporal.ChronoField.DAY_OF_MONTH
        );

        int hijriMonth = hijri.get(
            java.time.temporal.ChronoField.MONTH_OF_YEAR
        );

        FastingDays fasting = new FastingDays();

        /*
            Monday & Thursday fasting
        */
        DayOfWeek weekday = today.getDayOfWeek();

        fasting.setMondayThursday(
            weekday == DayOfWeek.MONDAY ||
            weekday == DayOfWeek.THURSDAY
        );

        /*
            White days:
            13th, 14th, 15th Hijri
        */
        fasting.setWhiteDays(
            hijriDay == 13 ||
            hijriDay == 14 ||
            hijriDay == 15
        );

        /*
            Ramadan
            Month 9
        */
        if (hijriMonth == 9) {
            fasting.setRamadan(true);
            fasting.setRamadanDay(hijriDay);
        }
        else {
            fasting.setRamadan(false);
        }

        /*
            Voluntary fasting recommendations
        */
        fasting.setVoluntaryRecommendations(
            Arrays.asList(
                "Six days of Shawwal after Ramadan",
                "Day of Arafah (9th Dhul Hijjah)",
                "Ashura (10th Muharram)",
                "Fasting in Sha'ban",
                "Fasting alternate days (fast of Prophet Dawud ﷺ)"
            )
        );

        return fasting;
    }
}
