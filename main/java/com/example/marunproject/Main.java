package com.example.marunproject;

import com.example.marunproject.Tests.DatabaseTests;
import com.example.marunproject.Tests.UserTests;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //running test classes before everything else
        startTest();
        //900-640 ekran oranı
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logInscreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 640);
        stage.setTitle("Kreş Yönetim Sistemi");
        stage.setScene(scene);
        stage.setResizable(false);
        Database.getConnection();
        stage.show();
    }
    public void startTest(){
        UserTests uTest = new UserTests();
        uTest.initializeUserTest();

        DatabaseTests dbTest = new DatabaseTests();
        dbTest.initializeDatabaseTest();

    }
    public static void main(String[] args) {
        launch();
    }
}