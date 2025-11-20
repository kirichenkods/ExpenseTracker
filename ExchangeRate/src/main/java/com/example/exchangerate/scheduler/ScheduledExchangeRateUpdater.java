package com.example.exchangerate.scheduler;

import com.example.exchangerate.dto.ValCursDto;
import com.example.exchangerate.entity.CurrencyRate;
import com.example.exchangerate.mapper.CurrencyMapper;
import com.example.exchangerate.provider.ExchangeRateProvider;
import com.example.exchangerate.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledExchangeRateUpdater implements ExchangeRateUpdater {

    private final ExchangeRateProvider exchangeRateProvider;
    private final CurrencyMapper currencyMapper;
    private final CurrencyRateRepository repository;

    /**
     * Выполняет обновление таблицы курсов валют.
     * Получает данные, маппит в сущности и сохраняет в БД.
     */
    @Override
    @Scheduled(cron = "0 13 00 * * *", zone = "Europe/Moscow")
    public void updateRates() {
        log.info("=== Обновление курсов валют по расписанию: НАЧАЛО ===");

        try {
            ValCursDto valCursDto = exchangeRateProvider.getCurrentRates();
            List<CurrencyRate> currencyRateList = currencyMapper.fromDto(valCursDto);
            repository.saveAll(currencyRateList);
        } catch (Exception e) {
            log.error("Ошибка при обновлении курсов валют", e);
        }

        log.info("=== Обновление курсов валют: КОНЕЦ ===");
    }
}
