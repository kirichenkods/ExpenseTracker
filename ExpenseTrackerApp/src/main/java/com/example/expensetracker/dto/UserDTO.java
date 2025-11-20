package com.example.expensetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Schema(description = "Модель пользователя")
public class UserDTO {
    @Schema(description = "Уникальный идентификатор пользователя. " +
            "При создании пользователя не передается")
    private UUID id;

    @Schema(description = "Имя пользователя. Обязательно к заполнению",
            minimum = "2",
            maximum = "255")
    @NotNull(message = "Имя пользователя обязательно")
    @Size(min = 2, max = 255, message = "Имя должно содержать от 2 до 255 символов")
    private String name;
}