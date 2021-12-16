package com.example.marunproject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChildrenUpdateScreen {
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField allergy;
    @FXML
    TextField drugs;
    @FXML
    TextField username;
    @FXML
    TextField age;
    @FXML
    Button saveButton;
    public void setUsername(String username){
        this.username.setText(username);
    }
    public void onSaveButton(){
        if(name.getText().equals("") ||surname.getText().equals("") || age.getText().equals("") || allergy.getText().equals("") || drugs.getText().equals("") || username.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("İlgili alanlar boş bırakılamaz, eğer kullanılan herhangi bir alerji veya ilaç yoksa - yazabilirsiniz.");
            alert.setHeaderText("Hata");
        }
        String age = this.age.getText();
        try{
            Integer.parseInt(age);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hata");
            alert.setContentText("Yaş kısmını rakamlarla yazmalısınız");
        }
        //saving to database
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO children(parentusername, name, surname, allergy, drugs, age)  VALUES('"+username.getText()+"','"+name.getText()+"','"+surname.getText()+"','"+allergy.getText()+"','"+drugs.getText()+"','"+age+"')");
            stmt.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
    }
}
