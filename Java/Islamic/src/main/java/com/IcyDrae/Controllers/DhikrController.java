package com.IcyDrae.Controllers;

import com.IcyDrae.Data.Dhikr;
import com.IcyDrae.Services.AdhkarService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DhikrController {
    @FXML
    private VBox dhikrContainer;
    private final AdhkarService service = new AdhkarService();

    @FXML
    public void initialize() throws Exception {
        loadDhikr();
    }

    private void loadDhikr() throws Exception {
        for (Dhikr dhikr : service.getAll()) {
            VBox card = createDhikrCard(dhikr);
            dhikrContainer.getChildren()
                    .add(card);
        }
    }

    private VBox createDhikrCard(Dhikr dhikr) {
        Label arabic = new Label(
                dhikr.getText()
        );
        arabic.setStyle(
                """
                -fx-font-size:28px;
                -fx-font-weight:bold;
                -fx-text-alignment:center;
                """
        );

        Label english = new Label(
                dhikr.getTranslation()
        );

        english.setStyle(
            """
                -fx-font-size:16px;
                """
                );

        String timeLabel = "";
        if (dhikr.getCount() == 1) {
            timeLabel = " time";
        } else if (dhikr.getCount() > 1) {
            timeLabel = " times";
        }

        Label count = new Label(
            "Repeat: " + dhikr.getCount() + timeLabel
        );

        count.setStyle(
            """
            -fx-font-size:16px;
            -fx-font-weight:bold;
            -fx-text-fill:#C9A24A;
            """
        );

        VBox card = new VBox(
            10,
            arabic,
            english,
            count
        );

        card.setAlignment(
                javafx.geometry.Pos.CENTER
        );

        card.setPrefWidth(700);

        card.setStyle(
                """
                -fx-background-color:white;
                -fx-padding:25;
                -fx-background-radius:12;
                -fx-border-radius:12;
                -fx-border-color:#C9A24A;
                -fx-border-width:1;
                """
        );

        return card;
    }
}
