package com.example.expensetracker.service;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.mapper.UserMapper;
import com.example.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public void updateUser(UserDTO userDTO) throws UserNotFoundException {
        UUID id = userDTO.getId();
        User user = getUserById(id);
        repository.updateUserName(userDTO.getName(), id);
    }

    @Override
    public User getUserById(UUID id) throws UserNotFoundException {
        Optional<User> userOpt = repository.getUserById(id);
        return userOpt.orElseThrow(() ->
                new UserNotFoundException("пользователь с id " + id  + " не найден"));
    }
}
