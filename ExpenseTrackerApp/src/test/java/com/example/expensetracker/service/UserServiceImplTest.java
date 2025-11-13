package com.example.expensetracker.service;

import com.example.expensetracker.dto.UserDTO;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.mapper.UserMapper;
import com.example.expensetracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_shouldReturnUserId() {
        UserDTO dto = UserDTO.builder().name("John").build();
        User user = new User();
        UUID expectedId = UUID.randomUUID();
        user.setId(expectedId);

        when(userMapper.toEntity(dto)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);

        UUID actualId = service.createUser(dto);

        assertEquals(expectedId, actualId);
        verify(userMapper).toEntity(dto);
        verify(repository).save(user);
    }

    @Test
    void deleteUser_shouldCallRepositoryDeleteById() {
        UUID id = UUID.randomUUID();

        service.deleteUser(id);

        verify(repository).deleteById(id);
    }

    @Test
    void updateUser_whenUserExists_shouldUpdateUserName() throws UserNotFoundException {
        UUID id = UUID.randomUUID();
        UserDTO dto = UserDTO.builder()
                .id(id)
                .name("NewName")
                .build();
        User user = new User();
        user.setId(id);

        when(repository.getUserById(id)).thenReturn(Optional.of(user));

        service.updateUser(dto);

        verify(repository).updateUserName("NewName", id);
    }

    @Test
    void updateUser_whenUserNotFound_shouldThrowException() {
        UUID id = UUID.randomUUID();
        UserDTO dto = UserDTO.builder()
                .id(id)
                .name("NewName")
                .build();

        when(repository.getUserById(id)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> service.updateUser(dto));

        assertTrue(exception.getMessage().contains(id.toString()));
    }

    @Test
    void getUserById_whenUserExists_shouldReturnUser() throws UserNotFoundException {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);

        when(repository.getUserById(id)).thenReturn(Optional.of(user));

        User result = service.getUserById(id);

        assertEquals(user, result);
    }

    @Test
    void getUserById_whenUserNotFound_shouldThrowException() {
        UUID id = UUID.randomUUID();

        when(repository.getUserById(id)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> service.getUserById(id));

        assertTrue(exception.getMessage().contains(id.toString()));
    }
}