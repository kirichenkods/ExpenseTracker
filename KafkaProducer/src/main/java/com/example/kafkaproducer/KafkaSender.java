package com.example.kafkaproducer;

import com.example.domain.entity.CurrencyRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, CurrencyRate> kafkaTemplate;

    public void sendMessage(CurrencyRate currencyRate, String topicName) {
        log.info("Sending to kafka : {}", currencyRate.getCharCode());
        kafkaTemplate.send(topicName, currencyRate);
    }
}
