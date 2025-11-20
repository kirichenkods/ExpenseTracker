package com.example.expensetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Модель операции")
public class OperationDTO {
    @Schema(description = "Уникальный идентификатор операции. " +
            "При создании операции не передается")
    private UUID id;
    @NotNull(message = "Сумма не может быть пустой")
    @Positive(message = "Сумма должна быть больше нуля")
    @Schema(description = "Сумма. Должна быть положительной")
    private double amount;

    @Schema(description = "Признак дохода. true - доход, false - расход")
    private boolean isIncome;

    @Schema(description = "Дата операции, может быть пустой")
    private LocalDate date;

    @NotNull(message = "Категория не может быть пустой")
    @Schema(description = "Категория. Задается произвольно, но обязательна к заполнению")
    private String category;

    @Schema(description = "Комментарий к операции, может быть пустым")
    private String comment;

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    @Schema(description = "Уникальный идентификатор пользователя, обязателен к заполнению")
    private UUID user_id;
}
