package com.example.kafkaconsumer;

import com.example.domain.entity.CurrencyRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyKafkaListener {

    private final CurrencyRateHandler handler;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.group-id}")
    public void listen(CurrencyRate rate) {
        log.info("Received rate: {}", rate.getCharCode());
        handler.handle(rate);
    }
}
