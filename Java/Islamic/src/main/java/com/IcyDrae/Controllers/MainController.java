package com.IcyDrae.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    private StackPane mainContent;
    @FXML
    private Button prayerButton;
    @FXML
    private Button tomorrowButton;
    @FXML
    private Button dhikrButton;
    @FXML
    private Button quranButton;
    @FXML
    private Button hijriButton;
    @FXML
    private Button fastingButton;
    @FXML
    private Button namesButton;

    private String activePage = "Prayer";

    @FXML
    public void initialize() {
        // Later:
        // mainContent.getChildren()
        // .add(new PrayerPage());
        setActive("Prayer");
    }

    private void setActive(String page) {
        activePage = page;
        updateMenuStyles();
    }

    private void updateMenuStyles() {
        reset(prayerButton);
        reset(tomorrowButton);
        reset(dhikrButton);
        reset(quranButton);
        reset(hijriButton);
        reset(fastingButton);
        reset(namesButton);

        switch(activePage) {
            case "Prayer" ->
                    activate(prayerButton);
            case "Tomorrow" ->
                    activate(tomorrowButton);
            case "Dhikr" ->
                    activate(dhikrButton);
            case "Quran" ->
                    activate(quranButton);
            case "Hijri" ->
                    activate(hijriButton);
            case "Fasting" ->
                    activate(fastingButton);
            case "Names" ->
                    activate(namesButton);
        }
    }

    private void activate(Button button) {
        button.setStyle(
                """
                -fx-background-color:#C9A24A;
                -fx-text-fill:black;
                -fx-font-weight:bold;
                -fx-font-size:16px;
                """
        );

        button.setDisable(true);
    }

    private void reset(Button button) {
        button.setStyle(
                """
                -fx-background-color:transparent;
                -fx-text-fill:#E8E6E1;
                -fx-font-size:16px;
                """
        );
        button.setDisable(false);
    }

    @FXML
    private void prayerClicked() {
        // load Prayer page
        setActive("Prayer");
    }

    @FXML
    private void tomorrowClicked() {
        setActive("Tomorrow");
    }

    @FXML
    private void dhikrClicked() {
        setActive("Dhikr");
    }

    @FXML
    private void quranClicked() {
        setActive("Quran");
    }

    @FXML
    private void hijriClicked() {
        setActive("Hijri");
    }

    @FXML
    private void fastingClicked() {
        setActive("Fasting");
    }

    @FXML
    private void namesClicked() {
        setActive("Names");
    }
}
