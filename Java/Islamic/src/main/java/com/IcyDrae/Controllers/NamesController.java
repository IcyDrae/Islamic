package com.IcyDrae.Controllers;

import com.IcyDrae.App;
import com.IcyDrae.Data.NinetyNineNames;
import com.IcyDrae.Services.NinetyNineNamesService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.List;

public class NamesController {
    @FXML
    private GridPane namesGrid;
    private final NinetyNineNamesService service = new NinetyNineNamesService();

    @FXML
    public void initialize() throws Exception {
        loadNames();
    }

    private void loadNames() throws Exception {
        List<NinetyNineNames> names = service.getAll();

        int column = 0;
        int row = 0;
        for (int i = 0; i < names.size(); i++) {
            VBox card =
                createCard(
                    i + 1,
                    names.get(i)
                );

            namesGrid.add(
                card,
                column,
                row
            );

            column++;

            // 3 cards per row
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    private VBox createCard(int number, NinetyNineNames name) {
        VBox card = new VBox();

        card.setAlignment(
            Pos.CENTER
        );
        card.setSpacing(10);
        card.setPrefWidth(250);
        card.setPrefHeight(220);

        card.setStyle(
            """
            -fx-background-color:white;
            -fx-background-radius:15;
            -fx-padding:20;
            """
        );

        Label numberLabel =
            new Label(
                "#" + number
            );

        numberLabel.setStyle(
            """
            -fx-font-size:16px;
            -fx-text-fill:#C9A24A;
            """
        );

        Label arabic =
            new Label(
                name.getArabic()
            );
        arabic.setFont(App.arabicFont);
        arabic.setStyle(
            """
            -fx-text-fill:#123B3A;
            """
        );

        Label transliteration =
            new Label(
                name.getTransliteration()
            );

        transliteration.setStyle(
            """
            -fx-font-size:18px;
            -fx-text-fill:#C9A24A;
            """
        );

        Label english =
            new Label(
                name.getEnglish()
            );

        english.setStyle(
            """
            -fx-font-size:16px;
            -fx-text-fill:#123B3A;
            """
        );

        english.setWrapText(true);
        english.setAlignment(Pos.CENTER);

        card.getChildren()
            .addAll(
                numberLabel,
                arabic,
                transliteration,
                english
            );

        return card;
    }
}
