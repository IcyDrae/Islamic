package com.IcyDrae.Controllers;

import com.IcyDrae.Data.Quran.Ayah;
import com.IcyDrae.Data.Quran.Surah;
import com.IcyDrae.Services.QuranService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.geometry.NodeOrientation;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class QuranController {
    @FXML
    private ComboBox<Surah> surahSelector;
    @FXML
    private ScrollPane scrollPane;
    private final QuranService quranService = new QuranService();

    @FXML
    public void initialize() throws Exception {
        // Load 114 Surahs
        surahSelector.getItems().addAll(
            quranService.getSurahs()
        );

        surahSelector.getSelectionModel().selectFirst();
        loadSurah(
            surahSelector.getValue().getId()
        );
        surahSelector.setVisibleRowCount(5);
    }

    @FXML
    private void surahChanged() {
        Surah surah = surahSelector.getValue();

        if(surah == null)
            return;

        loadSurah(surah.getId());
    }

    private void loadSurah(int number) {
        try {
            Surah surah = quranService.getSurah(number);
            VBox flow = new VBox(20);
            flow.prefWidthProperty()
            .bind(scrollPane.widthProperty());
            flow.setAlignment(Pos.CENTER);
            flow.setStyle("""
                -fx-padding:30;
                -fx-background-color:#F5F3EE;
            """);

            // Surah title
            Text title = new Text(
                "\n" +
                surah.getName()
                + "\n"
                + surah.getTransliteration()
                + "\n\n"
            );

            title.setFont(
                Font.font(
            "Sans",
            28
                )
            );

            title.setStyle(
                """
                -fx-fill:#C9A24A;
                -fx-font-weight:bold;
                """
            );

            flow.getChildren().add(title);

            int ayahNumber = 1;
            for (Ayah ayah : surah.getVerses()) {
                /*
                * Top separator
                */
                Text separatorTop = new Text(
                    "\n✦ ────────────────────────────────── ✦\n\n"
                );

                separatorTop.setFont(
                    Font.font(
                "Sans",
                    22
                    )
                );

                separatorTop.setStyle(
                    """
                    -fx-fill:#C9A24A;
                    """
                );

                /*
                * Ayah number
                */
                Text number1 = new Text(
                    "﴿"
                    + ayahNumber
                    +
                    "﴾\n"
                );

                number1.setFont(
                    Font.font(
                "Noto Naskh Arabic Regular",
                22
                    )
                );

                number1.setStyle(
                    """
                    -fx-fill:#C9A24A;
                    """
                );

                /*
                * Arabic text
                */
                TextFlow arabicFlow = new TextFlow();
                arabicFlow.setMaxWidth(1000);
                arabicFlow.prefWidthProperty()
                        .bind(scrollPane.widthProperty().subtract(100));
                arabicFlow.setNodeOrientation(
                        NodeOrientation.RIGHT_TO_LEFT
                );
                arabicFlow.setTextAlignment(
                        TextAlignment.CENTER
                );
                Text arabic = new Text(
                    ayah.getText()
                    +
                    "\n\n"
                );
                arabic.setTextAlignment(TextAlignment.RIGHT);
                arabic.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

                arabic.setFont(
                    Font.font(
                        "Noto Naskh Arabic Regular",
                        32
                    )
                );
                arabic.setStyle(
                    """
                    -fx-fill:#123B3A;
                    """
                );
                arabicFlow.getChildren().add(arabic);

                /*
                * Translation
                */
                Text translation = new Text(
                    ayah.getTranslation()
                    +
                    "\n"
                );
                translation.wrappingWidthProperty()
                .bind(scrollPane.widthProperty().subtract(100));
                translation.setTextAlignment(TextAlignment.CENTER);
                translation.setFont(
                    Font.font(
                        "Sans",
                        20
                    )
                );

                translation.setStyle(
                    """
                    -fx-fill:#555555;
                    """
                );

                /*
                * Bottom spacing
                */
                Text spacing = new Text(
                    "\n\n"
                );

                flow.getChildren().addAll(
                    separatorTop,
                    number1,
                    arabicFlow,
                    translation,
                    spacing
                );
                ayahNumber++;
            }

            scrollPane.setContent(flow);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
