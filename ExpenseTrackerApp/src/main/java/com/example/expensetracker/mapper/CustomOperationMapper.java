package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.OperationDTO;
import com.example.expensetracker.entity.Operation;
import com.example.expensetracker.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomOperationMapper implements OperationMapper {
    @Override
    public OperationDTO toDTO(Operation entity) {
        return OperationDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .category(entity.getCategory())
                .isIncome(entity.isIncome())
                .date(entity.getDate())
                .comment(entity.getComment())
                .user_id(entity.getUser().getId())
                .build();
    }

    @Override
    public List<OperationDTO> toDTOs(List<Operation> entities) {
        return entities.stream()
                .map(this::toDTO).toList();
    }

    @Override
    public Operation toEntity(OperationDTO dto, User user) {
        return Operation.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .isIncome(dto.isIncome())
                .date(dto.getDate())
                .category(dto.getCategory())
                .comment(dto.getComment())
                .user(user)
                .build();
    }
}
