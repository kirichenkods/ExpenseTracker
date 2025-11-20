package com.example.expensetracker.service;

import com.example.expensetracker.dto.OperationDTO;
import com.example.expensetracker.entity.Operation;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.OperationNotFoundException;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.mapper.OperationMapper;
import com.example.expensetracker.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OperationServiceImplTest {

    @Mock
    private OperationMapper mapper;

    @Mock
    private OperationRepository repository;

    @Mock
    private UserService userService;

    @InjectMocks
    private OperationServiceImpl service;

    private UUID userId;
    private UUID operationId;
    private User user;
    private OperationDTO operationDTO;
    private Operation operation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userId = UUID.randomUUID();
        operationId = UUID.randomUUID();

        user = new User();
        user.setId(userId);

        operationDTO = OperationDTO.builder()
                .id(operationId)
                .user_id(userId)
                .amount(100.0)
                .isIncome(true)
                .category("Salary")
                .date(LocalDate.now())
                .comment("Test comment")
                .build();

        operation = Operation.builder()
                .id(operationId)
                .user(user)
                .amount(100.0)
                .isIncome(true)
                .category("Salary")
                .date(LocalDate.now())
                .comment("Test comment")
                .build();
    }

    @Test
    void createOperation_shouldReturnOperationId() throws Exception {
        when(userService.getUserById(userId)).thenReturn(user);
        when(mapper.toEntity(operationDTO, user)).thenReturn(operation);
        when(repository.save(operation)).thenReturn(operation);

        UUID id = service.createOperation(operationDTO);

        assertEquals(operationId, id);
        verify(userService).getUserById(userId);
        verify(mapper).toEntity(operationDTO, user);
        verify(repository).save(operation);
    }

    @Test
    void deleteOperation_shouldCallRepositoryDeleteById() {
        service.deleteOperation(operationId);
        verify(repository).deleteById(operationId);
    }

    @Test
    void updateOperation_whenOperationNotFound_shouldThrow() throws UserNotFoundException {
        when(userService.getUserById(userId)).thenReturn(user);
        when(repository.getOperationById(operationId)).thenReturn(Optional.empty());

        assertThrows(OperationNotFoundException.class,
                () -> service.updateOperation(operationDTO));

        verify(userService).getUserById(userId);
        verify(repository).getOperationById(operationId);
        verify(repository, never()).save(any());
    }

    @Test
    void getUserOperations_shouldReturnDtoList() throws UserNotFoundException {
        List<Operation> ops = List.of(operation);
        List<OperationDTO> dtoList = List.of(operationDTO);

        when(userService.getUserById(userId)).thenReturn(user);
        when(repository.findOperationsByUserId(userId)).thenReturn(ops);
        when(mapper.toDTOs(ops)).thenReturn(dtoList);

        List<OperationDTO> result = service.getUserOperations(userId);

        assertEquals(dtoList, result);
        verify(repository).findOperationsByUserId(userId);
    }

    @Test
    void getUserOperations_withIsIncome_shouldReturnDtoList() throws UserNotFoundException {
        boolean isIncome = true;
        List<Operation> ops = List.of(operation);
        List<OperationDTO> dtoList = List.of(operationDTO);

        when(userService.getUserById(userId)).thenReturn(user);
        when(repository.findAllByUserIdAndIsIncome(userId, isIncome)).thenReturn(ops);
        when(mapper.toDTOs(ops)).thenReturn(dtoList);

        List<OperationDTO> result = service.getUserOperations(userId, isIncome);

        assertEquals(dtoList, result);
        verify(repository).findAllByUserIdAndIsIncome(userId, isIncome);
    }

    @Test
    void getSumAmountByUser_shouldReturnSum() throws UserNotFoundException {
        boolean isIncome = true;
        double sum = 1234.56;

        when(userService.getUserById(userId)).thenReturn(user);
        when(repository.getSumAmountByUserId(userId, isIncome)).thenReturn(sum);

        double result = service.getSumAmountByUser(userId, isIncome);

        assertEquals(sum, result);
        verify(repository).getSumAmountByUserId(userId, isIncome);
    }

    @Test
    void getUserOperationsByPeriod_shouldReturnDtoList() throws UserNotFoundException {
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now();

        List<Operation> ops = List.of(operation);
        List<OperationDTO> dtoList = List.of(operationDTO);

        when(userService.getUserById(userId)).thenReturn(user);
        when(repository.findAllByUserIdAndDateBetween(userId, start, end)).thenReturn(ops);
        when(mapper.toDTOs(ops)).thenReturn(dtoList);

        List<OperationDTO> result = service.getUserOperationsByPeriod(userId, start, end);

        assertEquals(dtoList, result);
        verify(repository).findAllByUserIdAndDateBetween(userId, start, end);
    }

    @Test
    void getUserOperationsByPeriod_withIsIncome_shouldReturnDtoList() throws UserNotFoundException {
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now();
        boolean isIncome = true;

        List<Operation> ops = List.of(operation);
        List<OperationDTO> dtoList = List.of(operationDTO);

        when(userService.getUserById(userId)).thenReturn(user);
        when(repository
                .findAllByUserIdAndIncomeAndDateBetween(userId, start, end, isIncome))
                .thenReturn(ops);
        when(mapper.toDTOs(ops)).thenReturn(dtoList);

        List<OperationDTO> result = service
                .getUserOperationsByPeriod(userId, start, end, isIncome);

        assertEquals(dtoList, result);
        verify(repository)
                .findAllByUserIdAndIncomeAndDateBetween(userId, start, end, isIncome);
    }

    @Test
    void getOperations_shouldReturnDtoList() {
        List<Operation> ops = List.of(operation);
        List<OperationDTO> dtoList = List.of(operationDTO);

        when(repository.findAll()).thenReturn(ops);
        when(mapper.toDTOs(ops)).thenReturn(dtoList);

        List<OperationDTO> result = service.getOperations();

        assertEquals(dtoList, result);
        verify(repository).findAll();
    }

}