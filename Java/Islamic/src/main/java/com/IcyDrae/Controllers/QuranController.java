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

public class QuranController {
    @FXML
    private ComboBox<Integer> surahSelector;
    @FXML
    private ScrollPane scrollPane;
    private final QuranService quranService = new QuranService();

    @FXML
    public void initialize() {
        // Load 114 Surahs
        for(int i = 1; i <= 114; i++) {
            surahSelector.getItems()
                    .add(i);
        }

        surahSelector.setValue(1);
        loadSurah(1);
    }

    @FXML
    private void surahChanged() {
        Integer number =
                surahSelector.getValue();

        if(number == null)
            return;

        loadSurah(number);
    }

    private void loadSurah(int number) {
        try {
            Surah surah = quranService.getSurah(number);
            TextFlow flow = new TextFlow();

            flow.setNodeOrientation(
                NodeOrientation.RIGHT_TO_LEFT
            );

            flow.setTextAlignment(
                TextAlignment.CENTER
            );

            flow.setStyle(
                """
                -fx-padding:30;
                -fx-background-color:#F5F3EE;
                """
            );

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
                Text arabic = new Text(
                    ayah.getText()
                    +
                    "\n\n"
                );

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

                /*
                * Translation
                */
                Text translation = new Text(
                    ayah.getTranslation()
                    +
                    "\n"
                );
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
                    arabic,
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
