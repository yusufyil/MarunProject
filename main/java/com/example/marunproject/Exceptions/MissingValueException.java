package com.example.marunproject.Exceptions;

public class MissingValueException extends Exception{
    @Override
    public String toString() {
        return "Some values or checkboxes are missing.";
    }
}
