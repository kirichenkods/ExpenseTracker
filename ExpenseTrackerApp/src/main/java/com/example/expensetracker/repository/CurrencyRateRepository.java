package com.example.expensetracker.repository;


import com.example.domain.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, UUID> {

    Optional<CurrencyRate> findFirstByCharCodeAndDate(String charCode, LocalDate date);

}
