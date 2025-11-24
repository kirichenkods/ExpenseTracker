package com.example.expensetracker.controller;

import com.example.expensetracker.exception.CurrencyRateNotFoundException;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.service.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
@Tag(name = "Получение информации о разнице между внесенными доходами и расходами")
public class BalanceController {

    private final BalanceService service;

    @GetMapping("get/{uuid}/{charCode}")
    @Operation(summary = "Получение баланса в указанной валюте " +
            "uuid - uuid пользователя, " +
            "charCode - код валюты из стандарта ISO 4217 (например USD)")
    @ResponseStatus(HttpStatus.OK)
    public double getBalance(@PathVariable @NotNull UUID uuid,
                             @PathVariable @NotNull String charCode)
            throws UserNotFoundException, CurrencyRateNotFoundException {
        return service.getBalance(uuid, charCode);
    }

    @GetMapping("get/{uuid}")
    @Operation(summary = "Получение баланса по uuid пользователя")
    @ResponseStatus(HttpStatus.OK)
    public double getBalance(@PathVariable @NotNull UUID uuid)
            throws UserNotFoundException {
        return service.getBalance(uuid);
    }
}
