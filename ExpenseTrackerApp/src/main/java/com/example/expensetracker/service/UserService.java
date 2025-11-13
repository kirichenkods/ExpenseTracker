package com.example.expensetracker.service;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.UserNotFoundException;

import java.util.UUID;

public interface UserService {
    UUID createUser(UserDTO userDTO);

    void deleteUser(UUID uuid);

    void updateUser(UserDTO userDTO) throws UserNotFoundException;

    User getUserById(UUID id) throws UserNotFoundException;

}
