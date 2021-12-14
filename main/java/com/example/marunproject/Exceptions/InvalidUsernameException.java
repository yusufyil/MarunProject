package com.example.marunproject.Exceptions;

public class InvalidUsernameException extends Exception{
    @Override
    public String toString() {
        return "This username has already taken, you should choose another one";
    }
}
