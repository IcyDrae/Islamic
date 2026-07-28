package com.IcyDrae;

import com.IcyDrae.Data.FastingDays;
import com.IcyDrae.Services.FastingDaysService;

public class App {

    public static void main(String[] args) {
        FastingDaysService service = new FastingDaysService();
        FastingDays fasting = service.getFastingInfo();

        System.out.println("=== Recommended Fasting Days ===");
        System.out.println();

        if (fasting.isMondayThursday()) {
            System.out.println(
                "✓ Monday/Thursday fasting recommended today"
            );
        }

        if (fasting.isWhiteDays()) {
            System.out.println(
                "✓ White Day fasting recommended today"
            );
        }

        if (fasting.isRamadan()) {
            System.out.println(
                "✓ Ramadan fasting - Day "
                + fasting.getRamadanDay()
            );
        }

        System.out.println();
        System.out.println("Voluntary fasting:");

        for (String recommendation :
                fasting.getVoluntaryRecommendations()) {

            System.out.println(
                "- " + recommendation
            );
        }
    }
}