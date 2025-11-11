package com.example.exchangerate.provider;

import com.example.exchangerate.dto.ValCursDto;

import java.time.LocalDate;

public interface WebProvider {
    ValCursDto getValCursOnDate(LocalDate date);
}
