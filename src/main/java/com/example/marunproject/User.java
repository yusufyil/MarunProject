package com.example.marunproject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private String name;
    private String surName;
    private int age;
    private String sex;
    private String phoneNumber;
    private String secondPhoneNumber;
    private String adress;
    private String userName;
    private String password;
    private String userType;

    public User() {
        System.out.println("isimsiz bir kullanıcı oluşturuldu");
    }

    public User(String name, String surName, int age, String sex, String phoneNumber, String secondPhoneNumber, String adress, String userName, String password, String userType) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.adress = adress;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public boolean saveUser(){
        try{
            Connection conn = Database.getConnection();
            PreparedStatement posted = conn.prepareStatement("INSERT INTO users VALUES ('"+getUserName()+"','"+getPassword()+"','"+getName()+"','"+getSurName()+"','"+getAge()+"','"+getSex()+"','"+getPhoneNumber()+"','"+getSecondPhoneNumber()+"','"+getAdress()+"','"+getUserType()+"')");
            posted.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
        finally {
            System.out.println("işlem bitti.");
        }

        return true;
    }
    public void deleteUser(){
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(String.format("DELETE FROM USERS WHERE username = \"%s\"",userName));
            stmt.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println("Kullanıcı silme işlemi esnasında bir hata oluşutu.\n" + e);
        }
    }
    public void deleteUserFromApplications(){
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(String.format("DELETE FROM signupforms WHERE username = \"%s\"",userName));
            stmt.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println("Kullanıcı silme işlemi esnasında bir hata oluşutu.\n" + e);
        }
    }
    public void saveUserFromApplications(){
        User user = new User();
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stm = conn.prepareStatement(String.format("SELECT * FROM signupforms WHERE username = \"%s\"", userName));
            ResultSet resultSet = stm.executeQuery();
            while(resultSet.next()){
                user.setName(resultSet.getString("name"));
                user.setSurName(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setSecondPhoneNumber(resultSet.getString("secondphone"));
                user.setAdress(resultSet.getString("address"));
                user.setUserType(resultSet.getString("usertype"));
                user.setSex(resultSet.getString("sex"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            PreparedStatement stmt = conn.prepareStatement(String.format("DELETE FROM signupforms WHERE username = \"%s\"",user.userName));
            stmt.executeUpdate();
            conn.close();
        }
        catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
        user.saveUser();
    }
    //set and get methods..
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSurName() {

        return surName;
    }

    public void setSurName(String surName) {

        this.surName = surName;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {

        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
