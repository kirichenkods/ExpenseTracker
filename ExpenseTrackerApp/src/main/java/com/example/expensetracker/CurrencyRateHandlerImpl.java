package com.example.expensetracker;

import com.example.domain.entity.CurrencyRate;
import com.example.expensetracker.service.CurrencyService;
import com.example.kafkaconsumer.CurrencyRateHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateHandlerImpl implements CurrencyRateHandler {
    private final CurrencyService service;

    @Override
    public void handle(CurrencyRate rate) {
        service.save(rate);
        log.info("save rate");
    }
}
