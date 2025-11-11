package com.example.exchangerate.service;

import com.example.exchangerate.entity.CurrencyRate;
import com.example.exchangerate.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRateRepository currencyRateRepository;

    public List<CurrencyRate> getCurrencyRates() {
        return currencyRateRepository.findAll();
    }

    public CurrencyRate getRateByCharCodeAndDate(String charCode, LocalDate date) {
        return currencyRateRepository.findFirstByCharCodeAndDate(charCode, date);
    }

    public CurrencyRate getRateByCharCode(String charCode) {
        LocalDate currentDate = LocalDate.now();
        return getRateByCharCodeAndDate(charCode, currentDate);
    }
}
