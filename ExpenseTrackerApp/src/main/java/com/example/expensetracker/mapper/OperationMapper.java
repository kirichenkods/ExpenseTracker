package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.OperationDTO;
import com.example.expensetracker.entity.Operation;
import com.example.expensetracker.entity.User;

import java.util.List;

public interface OperationMapper {
    OperationDTO toDTO(Operation entity);

    List<OperationDTO> toDTOs(List<Operation> entities);

    Operation toEntity(OperationDTO dto, User user);
}
