package com.example.marunproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParentScreen {
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
    @FXML
    Label announcementHeader;
    @FXML
    TextArea announcementText;
    String username;
    @FXML
    Button update;
    @FXML
    Label kidName;
    @FXML
    Label kidSurname;
    @FXML
    Label kidAge;
    @FXML
    Label kidAllergy;
    @FXML
    Label kidDrugs;
    @FXML
    Button updateKid;
    public void setUsername(String username){
        this.username = username;
    }
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
    public void onUpdateButton(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userupdatescreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            UserUpdateScreen userUpdateScreen = loader.getController();
            userUpdateScreen.setUsername(username);
            stage.setScene(scene);
            stage.setTitle("Kullanıcı Bilgileri Güncelleme ekranı");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
    }
    public void displayAnnouncemenets(){
        //getting last announcement from db
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM announcements");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.last()){
                announcementHeader.setText(resultSet.getString("header"));
                announcementText.setText(resultSet.getString("message"));
                break;
            }
            conn.close();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
    }
    public void setChildrenScreen(){
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM children WHERE parentusername = " + String.format("\"%s\"",username));
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                kidName.setText(resultSet.getString("name"));
                kidSurname.setText(resultSet.getString("surname"));
                kidAge.setText(resultSet.getString("age"));
                kidAllergy.setText(resultSet.getString("allergy"));
                kidDrugs.setText(resultSet.getString("drugs"));
                break;
            }
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
    }
    public void onUpdateKid(){
        
    }
}
