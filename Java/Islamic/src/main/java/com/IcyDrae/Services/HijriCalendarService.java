package com.IcyDrae.Services;

import com.IcyDrae.Data.HijriCalendar;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class HijriCalendarService {
    private final String[] months =
    {
        "Muharram",
        "Safar",
        "Rabi' al-Awwal",
        "Rabi' al-Thani",
        "Jumada al-Ula",
        "Jumada al-Akhirah",
        "Rajab",
        "Sha'ban",
        "Ramadan",
        "Shawwal",
        "Dhul Qa'dah",
        "Dhul Hijjah"
    };

    public HijriCalendarService() {

    }

    public HijriCalendar getCurrentHijriDate() {
        LocalDate today = LocalDate.now();
        HijrahDate hijriDate = HijrahDate.from(today);

        int month = hijriDate.get(java.time.temporal.ChronoField.MONTH_OF_YEAR);
        int day = hijriDate.get(java.time.temporal.ChronoField.DAY_OF_MONTH);
        int year = hijriDate.get(java.time.temporal.ChronoField.YEAR);

        HijriCalendar calendar = new HijriCalendar();
        calendar.setDay(
            hijriDate.get(java.time.temporal.ChronoField.DAY_OF_MONTH)
        );
        calendar.setMonth(
            hijriDate.get(java.time.temporal.ChronoField.MONTH_OF_YEAR)
        );
        calendar.setMonthName(
            this.months[month - 1]
        );
        calendar.setYear(
            hijriDate.get(java.time.temporal.ChronoField.YEAR)
        );

        // Ramadan logic
        if (month < 9) {
            calendar.setRamadan(false);

            calendar.setDaysUntilRamadan(
                calculateDaysUntilRamadan(
                    day,
                    month,
                    year
                )
            );
        }
        else if (month == 9) {
            calendar.setRamadan(true);
            calendar.setDayOfRamadan(day);
        }
        else {
            calendar.setRamadan(false);
            calendar.setDaysUntilRamadan(0);
        }
        // Ramadan logic end

        DayOfWeek dayOfWeek = today.getDayOfWeek();

        calendar.setDayOfWeek(
            dayOfWeek.getDisplayName(
                TextStyle.FULL,
                Locale.ENGLISH
            )
        );

        calendar.setWeek(
            today.get(java.time.temporal.WeekFields.ISO.weekOfWeekBasedYear())
        );

        return calendar;
    }

    public int calculateDaysUntilRamadan(int currentDay, int currentMonth, int year) {
        int days = 0;

        // Remaining days in current month
        int currentMonthLength = 
            HijrahDate.of(
                year,
                currentMonth,
                currentDay
            ).lengthOfMonth();

        days += currentMonthLength - currentDay;

        // Months between current month and Ramadan (month 9)
        for (int month = currentMonth + 1; month < 9; month++) {
            days += HijrahDate.of(
                year,
                month,
                1
            ).lengthOfMonth();
        }

        return days;
    }
}
