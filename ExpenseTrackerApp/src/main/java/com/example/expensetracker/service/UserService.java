package com.example.expensetracker.service;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.entity.User;

import java.util.UUID;

public interface UserService {
    UUID createUser(UserDTO userDTO);

    void deleteUser(UUID uuid);

    void updateUser(UserDTO userDTO);

    User getUserById(UUID id);

}
