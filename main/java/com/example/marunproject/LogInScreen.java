package com.example.marunproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInScreen {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label header;
    @FXML
    TextField userNameInput;
    @FXML
    Label userName;
    @FXML
    Label password;
    @FXML
    PasswordField passwordInput;
    @FXML
    Button enterButton;
    @FXML
    Button signUpButton;
    @FXML
    Hyperlink myLink;
    public void setEnterButton(ActionEvent event){
        //System.out.println("Enter");
        final String userName = userNameInput.getText();
        final String password = passwordInput.getText();
        System.out.println("girdiler = " + userName +" " + password);
        switchToUserScene(event);

    }
    public void setSignUpButton(ActionEvent event){
        System.out.println("SignUp");
    }
    public void switchToUserScene(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("teacherscreen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Ekran değişimi sırasında bir hata meydana geldi.\n" + e);
        }

    }

}
