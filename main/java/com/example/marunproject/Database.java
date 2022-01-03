package com.example.marunproject;
import com.example.marunproject.Exceptions.InvalidPasswordException;
import com.example.marunproject.Exceptions.MissingValueException;
import com.example.marunproject.Exceptions.UserNotFoundException;
import com.sun.jdi.connect.Connector;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Database {
    public static Connection getConnection(){
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/javadb?characterEncoding=utf8&useConfigs=maxPerformance";
            String userName = "root";
            String password = "Y46513y";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,userName,password);
            System.out.println("Database bağlantısı kuruldu");
            return conn;
        }catch (Exception e){
            System.out.println("Bir hata oluştu\n" + e);
        }
        return null;
    }
    
    public static boolean isValidUser(String userName, String password) throws  UserNotFoundException{
        Connection conn = getConnection();
        try{
            PreparedStatement stm = conn.prepareStatement(String.format("SELECT username, password FROM users WHERE username = \"%s\"", userName));
            ResultSet resultSet = stm.executeQuery();
            while(resultSet.next()){
                if(userName.equals(resultSet.getString("username")) && password.equals(resultSet.getString("password"))){
                    return true;
                }
                else if(userName.equals(resultSet.getString("username"))){
                    return false;
                }
                else{
                    throw new UserNotFoundException();
                }
            }
        }catch (Exception e){
            System.out.println("Bir hata oluştu **.\n" + e);
        }
        return false;
    }
    public static User getUser(String userName){
        User user = new User();
        try{
            Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(String.format("SELECT * FROM users WHERE username = \"%s\"", userName));
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
        }
        catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
        return user;
    }
    public static User getUserFromApplication(String userName){
        User user = new User();
        try{
            Connection conn = getConnection();
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
        }
        catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
        return user;
    }
    public static boolean isValidUsername(String username){
        boolean valid = false;
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(String.format("SELECT username FROM users WHERE username = \"%s\"",username));
            ResultSet resultSet = stmt.executeQuery();
            //resultSet.
            while (resultSet.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
        return valid;
    }
    public static boolean isTeacher(String username){
        User user = getUser(username);
        if (user.getUserType().equals("teacher")){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean isParent(String username){
        User user = getUser(username);
        if (user.getUserType().equals("parent")){
            return true;
        }
        else{
            return false;
        }
    }
}
