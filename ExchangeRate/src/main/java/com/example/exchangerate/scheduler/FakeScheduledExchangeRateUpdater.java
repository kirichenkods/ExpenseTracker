package com.example.exchangerate.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FakeScheduledExchangeRateUpdater implements ExchangeRateUpdater {
    @Override
    @Scheduled(fixedDelay = 300_000)
    public void updateRates() {
        log.info("Обновляю курсы валют");
    }
}
