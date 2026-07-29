package com.IcyDrae.Controllers;

import com.IcyDrae.Data.FastingDays;
import com.IcyDrae.Services.FastingDaysService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FastingController {
    @FXML
    private Label ramadanLabel;
    @FXML
    private Label weeklyLabel;
    @FXML
    private Label whiteDaysLabel;
    @FXML
    private Label voluntaryLabel;

    private final FastingDaysService service = new FastingDaysService();

    @FXML
    public void initialize() {
        load();
    }

    private void load() {
        FastingDays fasting = service.getFastingInfo();

        /*
         Ramadan
        */
        if (fasting.isRamadan()) {
            ramadanLabel.setText(
                """
                Ramadan Mubarak!

                Today is Ramadan day %d.
                May Allah accept your fasting.
                """
                .formatted(
                    fasting.getRamadanDay()
                )
            );
        }
        else {
            ramadanLabel.setText(
                """
                It is currently not Ramadan.

                Ramadan is the 9th month of the Hijri calendar.
                """
            );
        }


        /*
          Monday Thursday
        */
        if (fasting.isMondayThursday()) {
            weeklyLabel.setText(
                """
                Today is a recommended fasting day.

                The Prophet ﷺ used to fast on Mondays and Thursdays.
                """
            );
        }
        else {
            weeklyLabel.setText(
                    """
                    Mondays and Thursdays are recommended
                    voluntary fasting days.
                    """
            );
        }

        /*
          White days
        */
        if (fasting.isWhiteDays()) {
            whiteDaysLabel.setText(
                    """
                    Today is one of the White Days.

                    The 13th, 14th and 15th of every Hijri month
                    are recommended fasting days.
                    """
            );
        }
        else {
            whiteDaysLabel.setText(
                    """
                    White Days are:

                    13th, 14th and 15th of every Hijri month.
                    """
            );
        }

        /*
          Voluntary
        */
        StringBuilder text = new StringBuilder();

        for(String recommendation : fasting.getVoluntaryRecommendations()) {
            text.append("• ")
                .append(recommendation)
                .append("\n");
        }
        voluntaryLabel.setText(
            text.toString()
        );
    }
}
