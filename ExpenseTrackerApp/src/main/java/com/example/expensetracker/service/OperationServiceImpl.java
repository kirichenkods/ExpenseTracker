package com.example.expensetracker.service;

import com.example.expensetracker.dto.OperationDTO;
import com.example.expensetracker.entity.Operation;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.mapper.OperationMapper;
import com.example.expensetracker.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationMapper mapper;
    private final OperationRepository repository;
    private final UserService userService;

    @Override
    public UUID createOperation(OperationDTO operationDTO) {
        User user = userService.getUserById(operationDTO.getUser_id());
        Operation op = mapper.toEntity(operationDTO, user);
        Operation opSaved = repository.save(op);
        return opSaved.getId();
    }

    @Override
    public void deleteOperation(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public void updateOperation(OperationDTO dto) {
        User user = userService.getUserById(dto.getUser_id());
        Operation newOperation = mapper.toEntity(dto, user);
        repository.save(newOperation);
    }

    @Override
    public List<OperationDTO> getUserOperations(UUID userUuid) {
        List<Operation> operations = repository.findOperationsByUserId(userUuid);
        return mapper.toDTOs(operations);
    }

    @Override
    public List<OperationDTO> getUserOperations(UUID userUuid, boolean isIncome) {
        List<Operation> operations =
                repository.findOperationsByUserIdAndIsIncome(userUuid, isIncome);
        return mapper.toDTOs(operations);
    }

    @Override
    public List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                        LocalDate dateStart,
                                                        LocalDate dateEnd) {
        List<Operation> operations =
                repository.findOperationsByUserIdAndDateBetween(
                        userUuid,
                        dateStart,
                        dateEnd);
        return mapper.toDTOs(operations);
    }

    @Override
    public List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                        LocalDate dateStart,
                                                        LocalDate dateEnd,
                                                        boolean isIncome) {
        List<Operation> operations =
                repository.findOperationsByUserIdAndIncomeAndDateBetween(
                        userUuid,
                        dateStart,
                        dateEnd,
                        isIncome);
        return mapper.toDTOs(operations);
    }

    @Override
    public List<OperationDTO> getOperations() {
        List<Operation> operations = repository.findAll();
        return mapper.toDTOs(operations);
    }
}
