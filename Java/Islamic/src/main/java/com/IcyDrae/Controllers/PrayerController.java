package com.IcyDrae.Controllers;

import com.IcyDrae.Data.PrayerTimesResponse;
import com.IcyDrae.Services.PrayerTimesService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrayerController {
    @FXML
    private Label fajrLabel;
    @FXML
    private Label sunriseLabel;
    @FXML
    private Label dhuhrLabel;
    @FXML
    private Label asrLabel;
    @FXML
    private Label maghribLabel;
    @FXML
    private Label ishaLabel;

    @FXML
    public void initialize() throws Exception {
        PrayerTimesService service = new PrayerTimesService();
        PrayerTimesResponse prayer = service.fetchForToday();

        fajrLabel.setText("Fajr: " + prayer.getData().getTimings().getFajr());
        sunriseLabel.setText("Sunrise: " + prayer.getData().getTimings().getSunrise());
        dhuhrLabel.setText("Dhuhr: " + prayer.getData().getTimings().getDhuhr());
        asrLabel.setText("Asr: " + prayer.getData().getTimings().getAsr());
        maghribLabel.setText("Maghrib: " + prayer.getData().getTimings().getMaghrib());
        ishaLabel.setText("Isha: " + prayer.getData().getTimings().getIsha());
    }
}
