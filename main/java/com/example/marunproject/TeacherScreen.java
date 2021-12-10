package com.example.marunproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TeacherScreen {
    @FXML
    Label nameField;
    @FXML
    Label surnameField;
    @FXML
    Label phoneField;
    @FXML
    Label secondPhoneField;
    @FXML
    Label adressField;
    @FXML
    Label userTypeField;
    public void setNameField(String name){
        nameField.setText(name);
    }
    public void setSurnameField(String surName){
        surnameField.setText(surName);
    }
    public void setPhoneField(String phone){
        phoneField.setText(phone);
    }
    public void setSecondPhoneField(String secondPhone){
        secondPhoneField.setText(secondPhone);
    }
    public void setAdressField(String adress){
        adressField.setText(adress);
    }
    public void setUserTypeField(String userType){
        userTypeField.setText(userType);
    }
}
