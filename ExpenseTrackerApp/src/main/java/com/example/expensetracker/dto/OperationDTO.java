package com.example.expensetracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
public class OperationDTO {
    private UUID id;
    @NotNull(message = "Сумма не может быть пустой")
    @Positive(message = "Сумма должна быть больше нуля")
    private double amount;

    private boolean isIncome;

    private LocalDate date;

    @NotNull(message = "Категория не может быть пустой")
    private String category;

    private String comment;

    //    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private UUID user_id;
}
