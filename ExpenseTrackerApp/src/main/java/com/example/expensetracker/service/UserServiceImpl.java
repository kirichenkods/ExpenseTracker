package com.example.expensetracker.service;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.mapper.UserMapper;
import com.example.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UUID createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = repository.save(user);
        return savedUser.getId();
    }

    @Override
    public void deleteUser(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        repository.updateUserName(userDTO.getName(), userDTO.getId());
    }

    @Override
    public User getUserById(UUID id) {
        return repository.getReferenceById(id);
    }
}
