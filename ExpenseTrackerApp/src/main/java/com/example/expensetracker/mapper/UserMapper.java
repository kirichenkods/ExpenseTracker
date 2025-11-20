package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.entity.User;

public interface UserMapper {
    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);
}
