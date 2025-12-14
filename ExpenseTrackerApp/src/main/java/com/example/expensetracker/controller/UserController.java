package com.example.expensetracker.controller;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Управление пользователями")
public class UserController {

    private final UserService service;

    @PostMapping("create")
    @Operation(summary = "Создание пользователя")
    public UUID createUser(@RequestBody @Valid UserDTO userDTO) {
        return service.createUser(userDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("update")
    @Operation(summary = "Обновление пользователя")
    public void updateUser(@RequestBody @Valid UserDTO userDTO) throws
            UserNotFoundException {
        service.updateUser(userDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("delete/{uuid}")
    @Operation(summary = "Удаление пользователя")
    public void deleteUser(@PathVariable UUID uuid) {
        service.deleteUser(uuid);
    }
}
