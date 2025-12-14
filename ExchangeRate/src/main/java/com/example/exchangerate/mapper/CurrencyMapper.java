package com.example.exchangerate.mapper;

import com.example.domain.entity.CurrencyRate;
import com.example.exchangerate.dto.ValCursDto;


import java.util.List;

public interface CurrencyMapper {
    List<CurrencyRate> fromDto(ValCursDto dto);
}
