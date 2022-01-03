package com.example.marunproject.Tests;

import com.example.marunproject.Database;
import com.example.marunproject.Exceptions.UserNotFoundException;
import com.example.marunproject.User;

import java.sql.Connection;

public class DatabaseTests {
    public DatabaseTests() {
    }
    public boolean initializeDatabaseTest(){
        System.out.println("Testing getConnection method of Database class...");
        Connection connection = Database.getConnection();
        if (connection == null){
            System.out.println("Failed!");
            return false;
        }
        System.out.println("Passed!");
        User testUserTeacher = new User("Gökhan", "Özmen", 42, "M", "5433189657", "5459768436", "Tekirdağ/Çerkezköy", "gk123u","paspaspas","Teacher");
        User testUserParent = new User("Sude", "Yılmaz", 46, "F", "5413569715", "5345339867", "Ankara/Dikmen", "Sude1453", "321SuSudede123", "Parent");
        testUserTeacher.saveUser();
        testUserParent.saveUser();
        System.out.println("Testing isValidUser method of Database class..");
        try{
            if (Database.isValidUser(testUserTeacher.getUserName(), testUserTeacher.getPassword())){
                System.out.println("Passed 1. test!");
            }
            else{
                System.out.println("Failed!");
                return false;
            }
        } catch (UserNotFoundException e) {
            System.out.println("Failed!");
            return false;
        }
        try{
            if (Database.isValidUser(testUserParent.getUserName(), testUserParent.getPassword())){
                System.out.println("Passed 2. test");
            }
            else{
                System.out.println("Failed!");
                return false;
            }
        }catch (UserNotFoundException e){
            System.out.println("Failed");
            return false;
        }
        try{
            if (Database.isValidUser(testUserParent.getUserName(),"Wrong password on purpose")){
                System.out.println("Failed 3. test!");
                return false;
            }
            else{
                System.out.println("Passed 3. test!");
            }
        }catch (UserNotFoundException e){
            System.out.println("Failed 3. test!");
            return false;
        }
        System.out.println("Testing getUser method of Database class...");
        User tempUser = Database.getUser(testUserParent.getUserName());
        if(tempUser == null){
            System.out.println("Failed!");
            return false;
        }
        if (tempUser.getPassword().equals(testUserParent.getPassword())){
            System.out.println("Passed!");
        }
        else{
            System.out.println("Failed !");
            return false;
        }
        testUserParent.deleteUser();
        testUserTeacher.deleteUser();
        return true;
    }
}
