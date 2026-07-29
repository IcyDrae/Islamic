package com.IcyDrae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class App extends Application {
    public static Font arabicFont;

    @Override
    public void start(Stage stage) throws Exception {
        arabicFont = Font.loadFont(
            getClass()
            .getResourceAsStream("/Fonts/NotoNaskhArabic-Regular.ttf"),
            34
        );

        FXMLLoader loader =
                new FXMLLoader(
                    getClass()
                    .getResource("/Views/Main.fxml")
                );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Islamic");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
