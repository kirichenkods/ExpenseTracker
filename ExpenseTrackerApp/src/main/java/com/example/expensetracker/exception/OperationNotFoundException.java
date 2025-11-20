package com.example.expensetracker.exception;

public class OperationNotFoundException extends Exception {
    public OperationNotFoundException(String message) {
        super(message);
    }
}