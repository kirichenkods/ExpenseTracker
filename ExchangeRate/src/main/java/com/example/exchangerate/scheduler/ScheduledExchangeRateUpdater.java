package com.example.exchangerate.scheduler;


import com.example.domain.entity.CurrencyRate;
import com.example.exchangerate.dto.ValCursDto;
import com.example.exchangerate.mapper.CurrencyMapper;
import com.example.exchangerate.provider.ExchangeRateProvider;
import com.example.kafkaproducer.KafkaSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledExchangeRateUpdater implements ExchangeRateUpdater {

    private final ExchangeRateProvider exchangeRateProvider;
    private final CurrencyMapper currencyMapper;
    private final KafkaSender kafkaSender;
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    /**
     * Выполняет обновление таблицы курсов валют.
     * Получает данные, маппит в сущности и сохраняет в БД.
     */
    @Override
//    @Scheduled(cron = "0 13 00 * * *", zone = "Europe/Moscow")
    @Scheduled(fixedRate = 300_000)
    public void updateRates() {
        log.info("=== Обновление курсов валют по расписанию: НАЧАЛО ===");

        try {
            ValCursDto valCursDto = exchangeRateProvider.getCurrentRates();
            List<CurrencyRate> currencyRateList = currencyMapper.fromDto(valCursDto);
            log.info("Отправка в кафку");
            currencyRateList.forEach(rate -> kafkaSender.sendMessage(rate, topicName));
        } catch (Exception e) {
            log.error("Ошибка при обновлении курсов валют", e);
        }

        log.info("=== Обновление курсов валют: КОНЕЦ ===");
    }
}
