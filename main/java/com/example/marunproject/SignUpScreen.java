package com.example.marunproject;

import com.example.marunproject.Exceptions.InvalidPasswordException;
import com.example.marunproject.Exceptions.InvalidUsernameException;
import com.example.marunproject.Exceptions.MissingValueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignUpScreen {
    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextField phoneField;
    @FXML
    TextField secondPhoneField;
    @FXML
    TextField addressField;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    TextField ageField;
    @FXML
    ChoiceBox sexChoiceBox;
    @FXML
    ChoiceBox userTypeChoiceBox;
    @FXML
    Button signUpButton;

    public void onSignUpButton(ActionEvent event){
        String name = nameField.getText();
        String surname = surnameField.getText();
        String phone = phoneField.getText();
        String secondPhone = secondPhoneField.getText();
        String address = addressField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String age = ageField.getText();
        //i'll convert age into a string in later parts of code;
        String sex;
        if ("Erkek".equals((String) sexChoiceBox.getValue())){
            sex ="m";
        }
        else if("Kadın".equals((String) sexChoiceBox.getValue())){
            sex ="f";
        }
        else{
            sex = "o";
        }
        String userType;

        if("Öğretmen".equals((String)userTypeChoiceBox.getValue())){
            userType = "teacher";
        }
        else{
            userType ="parent";
        }

        try{
            //checking every textFiled to make sure any of them is not empty
            if(name.equals("") || surname.equals("")|| phone.equals("") || secondPhone.equals("") || address.equals("") || username.equals("") || password.equals("") || age.equals("")){
                throw new MissingValueException();
            }
            else if(password.length() < 8){
                throw new InvalidPasswordException();
            }
            else if (Database.isValidUsername(username)){
                throw new InvalidUsernameException();
            }
            //trying to invoke NumberFormatException
            Integer.parseInt(age);
        }catch (MissingValueException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hata");
            alert.setContentText("Eksik bilgi girdiniz lütfen boş yer bırakmayınız.");
            alert.show();
        }catch (InvalidPasswordException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hata");
            alert.setContentText("Yazdığınız şifre çok kısa lütfen daha güvenli bir şifre yazınız.");
            alert.show();
        }catch (InvalidUsernameException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hata");
            alert.setContentText("Yazdığınız kullanıcı adı zaten sisteme kayıtlı lütfen başka bir ad almayı deneyin.");
            alert.show();
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hata");
            alert.setContentText("Yaş kısmını rakamlarla yazmalısınız.");
            alert.show();
        }
        //SENDING FORM TO DATABASE
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO signupforms(name, surname, phone, secondphone, address, age, username, password, sex, usertype) VALUES('"+name+"','"+surname+"','"+phone+"','"+secondPhone+"','"+address+"','"+Integer.parseInt(age)+"','"+username+"','"+password+"','"+sex+"','"+userType+"')");
            stmt.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
    }
    public void setChoiceBoxes(){
        sexChoiceBox.getItems().add("Erkek");
        sexChoiceBox.getItems().add("Kadın");
        sexChoiceBox.getItems().add("Diğer");
        userTypeChoiceBox.getItems().add("Öğretmen");
        userTypeChoiceBox.getItems().add("Veli");
        sexChoiceBox.getSelectionModel().selectFirst();
        userTypeChoiceBox.getSelectionModel().selectFirst();
    }

}
