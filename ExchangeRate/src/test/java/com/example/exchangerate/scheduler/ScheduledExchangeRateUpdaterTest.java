package com.example.exchangerate.scheduler;

import com.example.exchangerate.dto.ValCursDto;
import com.example.exchangerate.entity.CurrencyRate;
import com.example.exchangerate.mapper.CurrencyMapper;
import com.example.exchangerate.provider.ExchangeRateProvider;
import com.example.exchangerate.repository.CurrencyRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class ScheduledExchangeRateUpdaterTest {
    @Mock
    private ExchangeRateProvider exchangeRateProvider;

    @Mock
    private CurrencyMapper currencyMapper;

    @Mock
    private CurrencyRateRepository repository;

    @InjectMocks
    private ScheduledExchangeRateUpdater updater;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateRates_successfulUpdate_shouldCallSaveAll() {
        ValCursDto valCursDto = mock(ValCursDto.class);
        List<CurrencyRate> currencyRateList = List.of(mock(CurrencyRate.class));

        when(exchangeRateProvider.getCurrentRates()).thenReturn(valCursDto);
        when(currencyMapper.fromDto(valCursDto)).thenReturn(currencyRateList);

        updater.updateRates();

        verify(exchangeRateProvider).getCurrentRates();
        verify(currencyMapper).fromDto(valCursDto);
        verify(repository).saveAll(currencyRateList);
    }

    @Test
    void updateRates_whenException_shouldLogErrorAndNotThrow() {
        when(exchangeRateProvider.getCurrentRates()).thenThrow(new RuntimeException("Ошибка"));

        updater.updateRates();

        verify(exchangeRateProvider).getCurrentRates();
        verify(currencyMapper, never()).fromDto(any());
        verify(repository, never()).saveAll(any());
    }
}