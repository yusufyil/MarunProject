package com.example.marunproject.Tests;

import com.example.marunproject.Database;
import com.example.marunproject.User;


public class UserTests {
    public UserTests() {

    }
    public boolean initializeUserTest(){
        System.out.println("Testing parameterized constructor of user class...");
        User testUser = new User("Yusuf", "Yıldırım", 21, "M", "5414097059", "5546041722", "İstanbul", "@yusuf5", "p@@ssw00rd","Parent");
        System.out.println("Passed!");
        System.out.println("Testing saveUser method of user class...");
        testUser.saveUser();
        User userFromDb = Database.getUser("@yusuf5");
        if (userFromDb.getPassword().equals(testUser.getPassword())){
            System.out.println("Passed!");
        }
        else{
            return false;
        }
        System.out.println("Testing deletUser method of user class...");
        userFromDb = Database.getUser("@yusufyil");
        if (userFromDb.getPassword().equals(testUser.getPassword())){
            System.out.println("Failed!");
            return false;
        }
        testUser.deleteUser();
        return true;
    }
}
