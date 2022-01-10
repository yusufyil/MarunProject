package com.example.marunproject.Exceptions;

public class UserNotFoundException extends Exception{
    @Override
    public String toString() {
        return "user cannot founded in database.";
    }
}
