package com.IcyDrae.Controllers;

import com.IcyDrae.Data.PrayerTimesResponse;
import com.IcyDrae.Services.PrayerTimesService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TomorrowController {
    @FXML
    private Label fajrLabelTomorrow;
    @FXML
    private Label sunriseLabelTomorrow;
    @FXML
    private Label dhuhrLabelTomorrow;
    @FXML
    private Label asrLabelTomorrow;
    @FXML
    private Label maghribLabelTomorrow;
    @FXML
    private Label ishaLabelTomorrow;

    @FXML
    public void initialize() throws Exception {
        PrayerTimesService service = new PrayerTimesService();
        PrayerTimesResponse prayer = service.fetchForTomorrow();

        fajrLabelTomorrow.setText("Fajr: " + prayer.getData().getTimings().getFajr());
        sunriseLabelTomorrow.setText("Sunrise: " + prayer.getData().getTimings().getSunrise());
        dhuhrLabelTomorrow.setText("Dhuhr: " + prayer.getData().getTimings().getDhuhr());
        asrLabelTomorrow.setText("Asr: " + prayer.getData().getTimings().getAsr());
        maghribLabelTomorrow.setText("Maghrib: " + prayer.getData().getTimings().getMaghrib());
        ishaLabelTomorrow.setText("Isha: " + prayer.getData().getTimings().getIsha());
    }
}
