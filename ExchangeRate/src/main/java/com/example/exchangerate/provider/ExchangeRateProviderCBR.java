package com.example.exchangerate.provider;

import com.example.exchangerate.dto.ValCursDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ExchangeRateProviderCBR implements ExchangeRateProvider {

    private final WebProvider webProvider;

    @Override
    public ValCursDto getCurrentRates() {
        LocalDate curDate = LocalDate.now();
        return webProvider.getValCursOnDate(curDate);
    }
}
