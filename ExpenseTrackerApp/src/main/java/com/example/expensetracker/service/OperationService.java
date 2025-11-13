package com.example.expensetracker.service;

import com.example.expensetracker.dto.OperationDTO;
import com.example.expensetracker.exception.OperationNotFoundException;
import com.example.expensetracker.exception.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface OperationService {
    UUID createOperation(OperationDTO operationDTO) throws UserNotFoundException;

    void deleteOperation(UUID uuid);

    void updateOperation(OperationDTO dto) throws UserNotFoundException, OperationNotFoundException;

    List<OperationDTO> getUserOperations(UUID userUuid) throws UserNotFoundException;

    List<OperationDTO> getUserOperations(UUID userUuid, boolean isIncome) throws UserNotFoundException;

    double getSumAmountByUser(UUID userUuid, boolean isIncome) throws UserNotFoundException;

    List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                 LocalDate dateStart,
                                                 LocalDate dateEnd) throws UserNotFoundException;

    List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                 LocalDate dateStart,
                                                 LocalDate dateEnd,
                                                 boolean isIncome) throws UserNotFoundException;

    List<OperationDTO> getOperations();
}
