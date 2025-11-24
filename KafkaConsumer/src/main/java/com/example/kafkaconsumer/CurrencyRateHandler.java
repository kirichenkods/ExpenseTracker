package com.example.kafkaconsumer;

import com.example.domain.entity.CurrencyRate;

public interface CurrencyRateHandler {
    void handle(CurrencyRate rate);
}