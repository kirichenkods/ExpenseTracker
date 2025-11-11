package com.example.exchangerate.mapper;

import com.example.exchangerate.dto.ValCursDto;
import com.example.exchangerate.entity.CurrencyRate;

import java.util.List;

public interface CurrencyMapper {
    List<CurrencyRate> fromDto(ValCursDto dto);
}
