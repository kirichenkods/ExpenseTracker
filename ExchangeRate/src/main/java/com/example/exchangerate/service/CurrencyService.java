package com.example.exchangerate.service;

import com.example.exchangerate.entity.CurrencyRate;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyService {
    List<CurrencyRate> getCurrencyRates();

    CurrencyRate getRateByCharCodeAndDate(String charCode, LocalDate date);

    CurrencyRate getRateByCharCode(String charCode);
}
