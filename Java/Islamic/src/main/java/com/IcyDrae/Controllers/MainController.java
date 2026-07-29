package com.IcyDrae.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    public void initialize() throws Exception {
        // Load default page
        loadPage("/Views/Prayer.fxml");
        setActive("Prayer");
    }

    private void loadPage(String path) throws Exception {
        Parent view = FXMLLoader.load(
                getClass().getResource(path)
        );
        mainContent.getChildren().setAll(view);
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

        switch (activePage) {
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
        // Do NOT disable it
        button.setDisable(false);
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
    private void prayerClicked() throws Exception {
        loadPage("/Views/Prayer.fxml");
        setActive("Prayer");
    }

    @FXML
    private void tomorrowClicked() throws Exception {
        loadPage("/Views/Tomorrow.fxml");
        setActive("Tomorrow");
    }

    @FXML
    private void dhikrClicked() throws Exception {
        loadPage("/Views/Dhikr.fxml");
        setActive("Dhikr");
    }

    @FXML
    private void quranClicked() throws Exception {
        loadPage("/Views/Quran.fxml");
        setActive("Quran");
    }

    @FXML
    private void hijriClicked() throws Exception {
        loadPage("/Views/Hijri.fxml");
        setActive("Hijri");
    }

    @FXML
    private void fastingClicked() throws Exception {
        loadPage("/Views/Fasting.fxml");
        setActive("Fasting");
    }

    @FXML
    private void namesClicked() throws Exception {
        loadPage("/Views/NinetyNineNames.fxml");
        setActive("Names");
    }
}
