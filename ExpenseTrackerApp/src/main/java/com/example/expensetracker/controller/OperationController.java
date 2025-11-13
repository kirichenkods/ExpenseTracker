package com.example.expensetracker.controller;

import com.example.expensetracker.dto.OperationDTO;
import com.example.expensetracker.exception.OperationNotFoundException;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/operations")
@RequiredArgsConstructor
@Tag(name = "Управление расходами/доходами")
public class OperationController {

    private final OperationService service;

    @PostMapping("create")
    @Operation(summary = "Создание расхода/дохода")
    public UUID createOperation(@RequestBody @Valid OperationDTO operationDTO) throws UserNotFoundException {
        return service.createOperation(operationDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("update")
    @Operation(summary = "Обновление данных операции")
    public void updateOperation(@RequestBody @Valid OperationDTO operationDTO)
            throws UserNotFoundException, OperationNotFoundException {
        service.updateOperation(operationDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("delete/{uuid}")
    @Operation(summary = "Удаление операции")
    public void deleteOperation(@PathVariable @NotNull UUID uuid) {
        service.deleteOperation(uuid);
    }

    @GetMapping
    @Operation(summary = "Получение всех операций")
    public List<OperationDTO> getAllOperations() {
        return service.getOperations();
    }

    @GetMapping("user/{userId}")
    @Operation(summary = "Получение всех операций пользователя")
    public List<OperationDTO> getUserOperations(@PathVariable @NotNull UUID userId) throws UserNotFoundException {
        return service.getUserOperations(userId);
    }

    @GetMapping("user/{userId}/{isIncome}")
    @Operation(summary = "Получение одного вида операций пользователя")
    public List<OperationDTO> getUserOperations(
            @PathVariable @NotNull UUID userId,
            @PathVariable @NotNull boolean isIncome) throws UserNotFoundException {
        return service.getUserOperations(userId, isIncome);
    }

    @GetMapping("user/{userId}/{dateStart}/{dateEnd}")
    @Operation(summary = "Получение всех операций пользователя за период")
    public List<OperationDTO> getUserOperationsByPeriod(
            @PathVariable @NotNull UUID userId,
            @PathVariable @NotNull LocalDate dateStart,
            @PathVariable @NotNull LocalDate dateEnd) throws UserNotFoundException {
        return service.getUserOperationsByPeriod(userId, dateStart, dateEnd);
    }

    @GetMapping("user/{userId}/{isIncome}/{dateStart}/{dateEnd}")
    @Operation(summary = "Получение одного вида операций пользователя за период")
    public List<OperationDTO> getUserOperationsByPeriod(
            @PathVariable @NotNull UUID userId,
            @PathVariable @NotNull boolean isIncome,
            @PathVariable @NotNull LocalDate dateStart,
            @PathVariable @NotNull LocalDate dateEnd) throws UserNotFoundException {
        return service.getUserOperationsByPeriod(
                userId,
                dateStart,
                dateEnd,
                isIncome);
    }
}
