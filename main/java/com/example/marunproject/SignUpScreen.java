package com.example.marunproject;

import com.example.marunproject.Exceptions.InvalidPasswordException;
import com.example.marunproject.Exceptions.InvalidUsernameException;
import com.example.marunproject.Exceptions.MissingValueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        String sex = (String) sexChoiceBox.getValue();
        String userType = (String) userTypeChoiceBox.getValue();

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
        }
        //SENDING FORM TO DATABASE
            
    }
    public void setChoiceBoxes(){
        sexChoiceBox.getItems().add("Erkek");
        sexChoiceBox.getItems().add("Kadın");
        sexChoiceBox.getItems().add("Diğer");
        userTypeChoiceBox.getItems().add("Öğretmen");
        userTypeChoiceBox.getItems().add("Öğrenci");
        sexChoiceBox.getSelectionModel().selectFirst();
        userTypeChoiceBox.getSelectionModel().selectFirst();
    }

}
