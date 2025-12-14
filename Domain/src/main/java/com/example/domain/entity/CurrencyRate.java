package com.example.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "currency_rates")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "date_rate", nullable = false)
    private LocalDate date;

    @Column(name = "char_code", nullable = false)
    private String charCode;

    @Column(name = "name")
    private String name;

    @Column(name = "unit_rate", nullable = false)
    private double unitRate;
}
