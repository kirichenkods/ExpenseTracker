package com.example.exchangerate.provider;

import com.example.exchangerate.dto.ValCursDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Реализация {@link WebProvider}, использующая {@link RestClient}
 * для запроса XML с курсами валют с сайта ЦБ РФ.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WebProviderRestClient implements WebProvider {

    private final RestClient restClient;

    @Value("${exchange_rate_provider.uri}")
    private String responseUri;

    /**
     * Отправляет GET-запрос к ЦБ для получения курсов валют на указанную дату.
     *
     * @param date дата, на которую нужно получить курс
     * @return десериализованный XML в виде {@link ValCursDto}
     */
    @Override
    public ValCursDto getValCursOnDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        log.info("Запрашиваем курсы валют с ЦБ за {}", formattedDate);

        ValCursDto response = restClient.get()
                .uri(responseUri, formattedDate)
                .retrieve()
                .body(ValCursDto.class);

        log.info("Получено: {} валют", response.getValuteDtos().size());

        return response;
    }
}
