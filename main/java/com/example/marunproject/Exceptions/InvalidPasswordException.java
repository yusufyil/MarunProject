package com.example.marunproject.Exceptions;

public class InvalidPasswordException extends Exception{
    @Override
    public String toString() {
        return "This not a valid password, could be too short or too long.";
    }
}
