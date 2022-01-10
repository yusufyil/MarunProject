package com.example.marunproject;

import com.example.marunproject.Exceptions.MissingValueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class TeacherScreen {
    private ArrayList<User> parentList;
    private int userIndex = 0;
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
    @FXML
    TextArea announcementText;
    @FXML
    Label announcementHeader;
    @FXML
    Label applicationName;
    @FXML
    Label applicationSurname;
    @FXML
    Label applicationUsertype;
    @FXML
    Label applicationUsername;
    @FXML
    Button applicationAcceptButton;
    @FXML
    Button applicationDenyButton;
    @FXML
    Label parentName;
    @FXML
    Label parentSurname;
    @FXML
    Label parentUsername;
    @FXML
    Button nextParent;
    @FXML
    Button deleteAccount;
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
    public void displayApplications(){
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM signupforms");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.last()){
                    applicationName.setText(resultSet.getString("name"));
                    applicationSurname.setText(resultSet.getString("surname"));
                    applicationUsertype.setText(resultSet.getString("usertype"));
                    applicationUsername.setText(resultSet.getString("username"));
                    break;
            }
            conn.close();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
    }
    public void onAcceptButton(){
        if(applicationName.getText().equals("Başvuru yok")){
            return;
        }
        else{
            User user = Database.getUserFromApplication(applicationUsername.getText());
            System.out.println(user.getName());
            user.saveUserFromApplications();
        }
    }
    public void onDenyButton(){
        if(applicationName.getText().equals("Başvuru yok")){
            return;
        }
        else{
            try{
                Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(String.format("DELETE FROM signupforms WHERE username= \"%s\"",applicationUsername.getText()));
                stmt.executeUpdate();
                conn.close();
            }catch (Exception e){
                System.out.println("Bir hata oluştu.\n" + e);
            }
        }
    }
    public void setDeleteParentScreen(){
        parentList = new ArrayList<>();
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT username FROM users WHERE usertype = " + String.format("\"parent\""));
            ResultSet resultSet = stmt.executeQuery();
            User user = new User();
            while(resultSet.next()){
                 user = Database.getUser(resultSet.getString("username"));
                parentList.add(user);
            }
        }catch (Exception e){
            System.out.println("Bir Hata oluştu.\n" + e);
        }
        //initializing first to the scene
        parentName.setText(parentList.get(userIndex).getName());
        parentSurname.setText(parentList.get(userIndex).getSurName());
        parentUsername.setText(parentList.get(userIndex).getUserName());
    }
    public void onNextButton(){
        userIndex +=1;
        userIndex %= parentList.size();
        parentName.setText(parentList.get(userIndex).getName());
        parentSurname.setText(parentList.get(userIndex).getSurName());
        parentUsername.setText(parentList.get(userIndex).getUserName());
    }
    public void onDeleteButton(){
        User user = new User();
        user = Database.getUser(parentUsername.getText());
        user.deleteUser();
        parentList.remove(userIndex);
        userIndex %= parentList.size();
        parentName.setText(parentList.get(userIndex).getName());
        parentSurname.setText(parentList.get(userIndex).getSurName());
        parentUsername.setText(parentList.get(userIndex).getUserName());
    }
}
