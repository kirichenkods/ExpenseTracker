package com.example.expensetracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDTO {
//    @NotNull(message = "Идентификатор пользователя обязателен")
    private UUID id;
    @NotNull(message = "Идентификатор пользователя обязателен")
    @Size(min = 1, max = 255, message = "Имя должно содержать от 2 до 255 символов")
    private String name;
//    private List<OperationDTO> operationDTOS;
}