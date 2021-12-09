package com.example.marunproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LogInScreen {
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
    public void setEnterButton(){
        //System.out.println("Enter");
        final String userName = userNameInput.getText();
        final String password = passwordInput.getText();
        System.out.println("girdiler = " + userName +" " + password);
    }
    public void setSignUpButton(){
        System.out.println("SignUp");
    }

}
