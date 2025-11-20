package com.example.exchangerate.provider;

import com.example.exchangerate.dto.ValCursDto;

public interface ExchangeRateProvider {

    ValCursDto getCurrentRates();
}
