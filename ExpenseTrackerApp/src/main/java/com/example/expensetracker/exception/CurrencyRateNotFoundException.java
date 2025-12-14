package com.example.expensetracker.exception;

public class CurrencyRateNotFoundException extends Exception {
    public CurrencyRateNotFoundException(String message) {
        super(message);
    }
}