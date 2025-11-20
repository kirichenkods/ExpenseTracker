package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CustomUserMapper implements UserMapper {
    @Override
    public UserDTO toDTO(User entity) {
        return UserDTO.builder()
                .name(entity.getName())
                .id(entity.getId())
                .build();
    }

    @Override
    public User toEntity(UserDTO dto) {
        return User.builder()
                .name(dto.getName())
                .id(dto.getId())
                .build();
    }
}
