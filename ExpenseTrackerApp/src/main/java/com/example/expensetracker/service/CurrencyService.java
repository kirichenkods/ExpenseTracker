package com.example.expensetracker.service;


import com.example.domain.entity.CurrencyRate;
import com.example.expensetracker.exception.CurrencyRateNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyService {
    List<CurrencyRate> getCurrencyRates();

    void save(CurrencyRate currencyRate);

    CurrencyRate getRateByCharCodeAndDate(String charCode, LocalDate date) throws CurrencyRateNotFoundException;

    CurrencyRate getRateByCharCode(String charCode) throws CurrencyRateNotFoundException;
}
