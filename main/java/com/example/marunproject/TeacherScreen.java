package com.example.marunproject;

import com.example.marunproject.Exceptions.MissingValueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
    @FXML
    Button update;
    @FXML
    TextField header;
    @FXML
    TextArea announcement;
    String username;
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
    public void onSendButton(ActionEvent event){
        String header = this.header.getText();
        String text = announcement.getText();
        try{
            if(header.equals("") || text.equals("")){
                throw new MissingValueException();
            }
        }catch (MissingValueException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hata");
            alert.setContentText("İlgili alanları boş bırakmayınız.");
            alert.show();
        }
        //SENDING TO DATABASE
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO announcements(header, message) VALUES('"+header+"','"+text+"')");
            stmt.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
    }
}
