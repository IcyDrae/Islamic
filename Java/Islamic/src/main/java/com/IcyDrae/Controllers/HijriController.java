package com.IcyDrae.Controllers;

import com.IcyDrae.Services.HijriCalendarService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.temporal.ChronoField;

public class HijriController {
    @FXML
    private Label monthLabel;
    @FXML
    private Label weekdayLabel;
    @FXML
    private GridPane calendarGrid;
    private HijriCalendarService service;

    private final String[] weekDays =
    {
        "Mon",
        "Tue",
        "Wed",
        "Thu",
        "Fri",
        "Sat",
        "Sun"
    };

    @FXML
    public void initialize() {
        service = new HijriCalendarService();
        buildCalendar();
    }

    private void buildCalendar() {
        HijrahDate today =
            HijrahDate.from(
                LocalDate.now()
            );

        int month =
            today.get(
                ChronoField.MONTH_OF_YEAR
            );

        int year =
            today.get(
                ChronoField.YEAR
            );

        monthLabel.setText(
            getMonthName(month)
            +
            " "
            +
            year
            +
            " AH"
        );

        weekdayLabel.setText(
            "Today: "
            +
            today.get(ChronoField.DAY_OF_MONTH)
            +
            " "
            +
            getMonthName(month)
        );

        calendarGrid.getChildren()
            .clear();

        // Week headers
        for(int i = 0; i < 7; i++) {
            Label day =
                new Label(
                    weekDays[i]
                );
            day.setStyle(
                """
                -fx-font-size:18px;
                -fx-font-weight:bold;
                -fx-text-fill:#123B3A;
                """
            );
            calendarGrid.add(
                day,
                i,
                0
            );
        }

        HijrahDate firstDay =
            HijrahDate.of(
                year,
                month,
                1
            );

        int firstWeekDay =
            firstDay
            .get(
                ChronoField.DAY_OF_WEEK
            );

        int daysInMonth = firstDay.lengthOfMonth();
        int row = 1;
        int column = firstWeekDay - 1;
        for(int day = 1; day <= daysInMonth; day++) {
            Label cell =
                createDayCell(
                    day,
                    day ==
                    today.get(ChronoField.DAY_OF_MONTH)
                );

            calendarGrid.add(
                cell,
                column,
                row
            );

            column++;

            if(column == 7) {
                column = 0;
                row++;
            }
        }
    }

    private Label createDayCell(int day, boolean today) {
        Label label =
            new Label(
                String.valueOf(day)
            );

        label.setPrefSize(
            70,
            70
        );

        label.setAlignment(
            javafx.geometry.Pos.CENTER
        );

        if(today) {
            label.setStyle(
                """
                -fx-background-color:#C9A24A;
                -fx-text-fill:black;
                -fx-font-size:24px;
                -fx-font-weight:bold;
                -fx-background-radius:50;
                """
            );
        }
        else {
            label.setStyle(
                """
                -fx-background-color:#123B3A;
                -fx-text-fill:white;
                -fx-font-size:22px;
                -fx-background-radius:50;
                """
            );
        }

        return label;
    }

    private String getMonthName(int month) {
        String[] months =
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

        return months[month - 1];
    }
}
