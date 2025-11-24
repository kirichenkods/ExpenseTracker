package com.example.expensetracker.service;


import com.example.domain.entity.CurrencyRate;
import com.example.expensetracker.exception.CurrencyRateNotFoundException;
import com.example.expensetracker.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRateRepository currencyRateRepository;

    public List<CurrencyRate> getCurrencyRates() {
        return currencyRateRepository.findAll();
    }

    public CurrencyRate getRateByCharCodeAndDate(String charCode, LocalDate date) throws CurrencyRateNotFoundException {
        Optional<CurrencyRate> currencyRate =
                currencyRateRepository.findFirstByCharCodeAndDate(charCode, date);
        return currencyRate.orElseThrow(
                () -> new CurrencyRateNotFoundException(
                        "Не удалось получить актуальный курс для " + charCode));
    }

    public CurrencyRate getRateByCharCode(String charCode) throws CurrencyRateNotFoundException {
        LocalDate currentDate = LocalDate.now();
        return getRateByCharCodeAndDate(charCode, currentDate);
    }

    public void save(CurrencyRate currencyRate) {
        currencyRateRepository.save(currencyRate);
    }
}
