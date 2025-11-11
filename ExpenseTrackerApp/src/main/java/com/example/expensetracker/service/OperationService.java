package com.example.expensetracker.service;

import com.example.expensetracker.dto.OperationDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface OperationService {
    UUID createOperation(OperationDTO operationDTO);

    void deleteOperation(UUID uuid);

    void updateOperation(OperationDTO dto);

    List<OperationDTO> getUserOperations(UUID userUuid);

    List<OperationDTO> getUserOperations(UUID userUuid, boolean isIncome);

    List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                 LocalDate dateStart,
                                                 LocalDate dateEnd);

    List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                 LocalDate dateStart,
                                                 LocalDate dateEnd,
                                                 boolean isIncome);

    List<OperationDTO> getOperations();
}
