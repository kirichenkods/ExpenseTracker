package com.example.exchangerate.repository;

import com.example.exchangerate.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, UUID> {

    CurrencyRate findFirstByCharCodeAndDate(String charCode, LocalDate date);

}
