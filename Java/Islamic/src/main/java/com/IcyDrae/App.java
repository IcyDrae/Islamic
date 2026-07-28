package com.IcyDrae;

import com.IcyDrae.Services.HijriCalendarService;
import com.IcyDrae.Data.HijriCalendar;

public class App
{
    public static void main(String[] args) throws Exception
    {

        HijriCalendarService service = new HijriCalendarService();

        HijriCalendar hijri = service.getCurrentHijriDate();

        if (hijri.isRamadan()) {
            System.out.println(
                "Ramadan Mubarak! Day " +
                hijri.getDayOfRamadan()
            );
        }
        else if (hijri.getDaysUntilRamadan() > 0) {
            System.out.println(
                "Days until Ramadan: " +
                hijri.getDaysUntilRamadan()
            );
        }

        System.out.println(
            hijri.getDay() + " " +
            hijri.getMonthName() + " " +
            hijri.getYear() + " AH"
        );

        System.out.println(
            hijri.getDayOfWeek()
        );
    }
}
