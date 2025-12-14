package com.example.exchangerate.mapper;

import com.example.domain.entity.CurrencyRate;
import com.example.exchangerate.dto.ValCursDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CustomCurrencyMapper implements CurrencyMapper {

    @Override
    public List<CurrencyRate> fromDto(ValCursDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dto.getDate(), formatter);

        return dto.getValuteDtos()
                .stream()
                .map(valDto -> CurrencyRate.builder()
                        .date(date)
                        .unitRate(Double.parseDouble(
                                valDto.getVUnitRate().replace(",","."))
                        )
                        .charCode(valDto.getCharCode())
                        .name(valDto.getName())
                        .build()
                )
                .toList();
    }
}
