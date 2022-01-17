package com.example.marunproject;

public class Teacher extends User{
    public Teacher() {
    }

    public Teacher(String name, String surName, int age, String sex, String phoneNumber, String secondPhoneNumber, String adress, String userName, String password, String userType) {
        super(name, surName, age, sex, phoneNumber, secondPhoneNumber, adress, userName, password, userType);
    }
}
