package com.example.expensetracker.service;

import com.example.expensetracker.exception.CurrencyRateNotFoundException;
import com.example.expensetracker.exception.UserNotFoundException;

import java.util.UUID;

public interface BalanceService {
    double getBalance(UUID userId) throws UserNotFoundException;
    double getBalance(UUID userId, String charCode) throws UserNotFoundException,
            CurrencyRateNotFoundException;
}
