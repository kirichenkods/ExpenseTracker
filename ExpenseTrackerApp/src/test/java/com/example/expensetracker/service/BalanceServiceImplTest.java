package com.example.expensetracker.service;

import com.example.exchangerate.entity.CurrencyRate;
import com.example.exchangerate.service.CurrencyService;
import com.example.expensetracker.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BalanceServiceImplTest {
    @Mock
    private CurrencyService currencyService;

    @Mock
    private OperationService operationService;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
    }

    @Test
    void getBalance_withoutCurrency_shouldCalculateIncomeMinusExpense() throws UserNotFoundException {
        double income = 1500.0;
        double expense = 500.0;

        when(operationService.getSumAmountByUser(userId, true)).thenReturn(income);
        when(operationService.getSumAmountByUser(userId, false)).thenReturn(expense);

        double balance = balanceService.getBalance(userId);

        assertEquals(1000.0, balance);
        verify(operationService).getSumAmountByUser(userId, true);
        verify(operationService).getSumAmountByUser(userId, false);
    }

    @Test
    void getBalance_withCurrency_shouldMultiplyByRate() throws UserNotFoundException {
        String charCode = "USD";
        double income = 1500.0;
        double expense = 500.0;
        double unitRate = 85.00;

        CurrencyRate rate = mock(CurrencyRate.class);
        when(rate.getUnitRate()).thenReturn(unitRate);

        when(currencyService.getRateByCharCode(charCode)).thenReturn(rate);
        when(operationService.getSumAmountByUser(userId, true)).thenReturn(income);
        when(operationService.getSumAmountByUser(userId, false)).thenReturn(expense);

        double balanceInCurrency = balanceService.getBalance(userId, charCode);

        assertEquals(unitRate * (income - expense), balanceInCurrency);
        verify(currencyService).getRateByCharCode(charCode);
        verify(operationService, times(2))
                .getSumAmountByUser(eq(userId), anyBoolean());
    }
}