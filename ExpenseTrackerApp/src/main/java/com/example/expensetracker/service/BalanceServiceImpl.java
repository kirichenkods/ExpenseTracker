package com.example.expensetracker.service;

import com.example.exchangerate.entity.CurrencyRate;
import com.example.exchangerate.service.CurrencyService;
import com.example.expensetracker.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final CurrencyService currencyService;
    private final OperationService operationService;

    /**
     * Вычисляет баланс пользователя
     * как разницу между суммой доходов и суммой расходов.
     * Баланс может быть отрицательным.
     *
     * @param userId уникальный идентификатор пользователя
     * @return баланс пользователя
     * @throws UserNotFoundException если пользователь с данным ID не найден
     */
    @Override
    public double getBalance(UUID userId) throws UserNotFoundException {

        double income = operationService.getSumAmountByUser(userId, true);
        double expense = operationService.getSumAmountByUser(userId, false);
        return income - expense;
    }

    /**
     * Вычисляет баланс пользователя в указанной валюте по курсу.
     *
     * @param userId уникальный идентификатор пользователя
     * @param charCode символьный код валюты (например, "USD", "EUR")
     * @return баланс пользователя, умноженный на курс валюты
     * @throws UserNotFoundException если пользователь с данным ID не найден
     */
    @Override
    public double getBalance(UUID userId, String charCode) throws UserNotFoundException {
        CurrencyRate rate = currencyService.getRateByCharCode(charCode);
        double balance = getBalance(userId);
        return rate.getUnitRate() * balance;
    }
}
