package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {

    @Query("SELECT o FROM Operation o WHERE o.user.id =:userId")
    List<Operation> findOperationsByUserId(UUID userId);

    Optional<Operation> getOperationById(UUID id);

    @Query("SELECT o " +
            "FROM Operation o " +
            "WHERE o.user.id =:userId AND o.isIncome =:isIncome")
    List<Operation> findAllByUserIdAndIsIncome(UUID userId,
                                               boolean isIncome);

    @Query("SELECT SUM (o.amount) " +
            "FROM Operation o " +
            "WHERE o.user.id =:userId AND o.isIncome =:isIncome")
    double getSumAmountByUserId(UUID userId, boolean isIncome);

    @Query("SELECT o " +
            "FROM Operation o " +
            "WHERE o.user.id =:userId AND o.date BETWEEN :dateStart AND :dateEnd")
    List<Operation> findAllByUserIdAndDateBetween(UUID userId,
                                                  LocalDate dateStart,
                                                  LocalDate dateEnd);

    @Query("SELECT o " +
            "FROM Operation o " +
            "WHERE o.user.id =:userId " +
            "AND o.date BETWEEN :dateStart AND :dateEnd " +
            "AND o.isIncome =:isIncome")
    List<Operation> findAllByUserIdAndIncomeAndDateBetween(UUID userId,
                                                           LocalDate dateStart,
                                                           LocalDate dateEnd,
                                                           boolean isIncome);

}
