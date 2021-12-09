package com.example.marunproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //900-640 ekran oranı
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logInscreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 640);
        stage.setTitle("Kreş Yönetim Sistemi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}