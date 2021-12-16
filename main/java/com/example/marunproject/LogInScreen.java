package com.example.marunproject;

import com.example.marunproject.Exceptions.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    public void setEnterButton(ActionEvent event){
        final String userName = userNameInput.getText();
        final String password = passwordInput.getText();
        if(userName.equals("") || password.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("HATA!");
            alert.setTitle("Marmara <3");
            alert.setContentText("Giriş yapabilmek için kullanıcı adı ve şifre kısımlarını doldurmalısınız.");
            alert.show();
        }else{
            try{
                if(Database.isValidUser(userName,password) && Database.isTeacher(userName)){
                    switchToTeacherScene(event, userName);
                }
                else if(Database.isValidUser(userName,password) && Database.isParent(userName)){
                    switchToParentScene(event, userName);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Marmara <3");
                    alert.setContentText("Yanlış şifre girdiniz.");
                    alert.setHeaderText("Eğer şifrenizi unuttuysanız sınıf öğretmeniyle ileitişme geçebilirsiniz.");
                    alert.show();
                }
            }catch (UserNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Marmara <3");
                alert.setHeaderText("Böyle bir kullanıcı yok.");
                alert.setContentText("Kullanıcı adını yanlış yazmış olabilirsiniz.");
                alert.show();
            }
        }

    }
    public void setSignUpButton(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signupscreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            SignUpScreen signUpScreen = loader.getController();
            signUpScreen.setChoiceBoxes();
            stage.setScene(scene);
            stage.setTitle("Kayıt Formu Ekranı");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }

    }
    public void switchToTeacherScene(ActionEvent event, String userName){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherscreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            TeacherScreen ts = loader.getController();
            User user = Database.getUser(userName);
            ts.setNameField(user.getName());
            ts.setSurnameField(user.getSurName());
            ts.setPhoneField(user.getPhoneNumber());
            ts.setSecondPhoneField(user.getSecondPhoneNumber());
            ts.setAdressField(user.getAdress());
            ts.setUserTypeField(user.getUserType());
            ts.setUsername(user.getUserName());
            ts.displayAnnouncemenets();
            ts.displayApplications();
            ts.setDeleteParentScreen();
        } catch (Exception e) {
            System.out.println("Ekran değişimi sırasında bir hata meydana geldi.\n" + e);
        }
    }
    public void switchToParentScene(ActionEvent event, String userName){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("parentscreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ParentScreen ps = loader.getController();
            User user = Database.getUser(userName);
            ps.setNameField(user.getName());
            ps.setSurnameField(user.getSurName());
            ps.setPhoneField(user.getPhoneNumber());
            ps.setSecondPhoneField(user.getSecondPhoneNumber());
            ps.setAdressField(user.getAdress());
            ps.setUserTypeField(user.getUserType());
            ps.setUsername(user.getUserName());
            ps.displayAnnouncemenets();
            ps.setChildrenScreen();
        } catch (Exception e) {
            System.out.println("Ekran değişimi sırasında bir hata meydana geldi.\n" + e);
        }
    }

}
