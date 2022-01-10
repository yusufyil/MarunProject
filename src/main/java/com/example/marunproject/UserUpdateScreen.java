package com.example.marunproject;

import com.example.marunproject.Exceptions.InvalidPasswordException;
import com.example.marunproject.Exceptions.MissingValueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserUpdateScreen {
    @FXML
    TextField phoneField;
    @FXML
    TextField secondPhoneField;
    @FXML
    TextField passwordField;
    @FXML
    TextField addressField;
    @FXML
    Button updateButton;
    String username;
    public void setUsername(String username){
        this.username = username;
    }
    public void onUpdateButton(ActionEvent event){
        String phone = phoneField.getText();
        String secondPhone = secondPhoneField.getText();
        String password = passwordField.getText();
        String address = addressField.getText();
        try{
            if(phone.equals("") || secondPhone.equals("") || password.equals("") || address.equals("")){
                throw new MissingValueException();
            }
            else if(password.length() < 8){
                throw new InvalidPasswordException();
            }
        } catch (MissingValueException e) {
            System.out.println("Bir hata oluştu.\n" + e);
        } catch (InvalidPasswordException e) {
            System.out.println("Bir hata oluştu.\n" + e);
        }
        //Updating user
        User user = Database.getUser(username);
        user.setPhoneNumber(phone);
        user.setSecondPhoneNumber(secondPhone);
        user.setPassword(password);
        user.setAdress(address);
        user.deleteUser();
        user.saveUser();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
