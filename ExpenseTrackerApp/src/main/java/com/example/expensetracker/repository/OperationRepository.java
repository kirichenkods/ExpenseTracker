package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {

    @Query("SELECT o FROM Operation o WHERE o.user.id =:userId")
    List<Operation> findOperationsByUserId(UUID userId);

    @Query("SELECT o " +
            "FROM Operation o " +
            "WHERE o.user.id =:userId AND o.isIncome =:isIncome")
    List<Operation> findOperationsByUserIdAndIsIncome(UUID userId,
                                                      boolean isIncome);

    @Query("SELECT o " +
            "FROM Operation o " +
            "WHERE o.user.id =:userId AND o.date BETWEEN :dateStart AND :dateEnd")
    List<Operation> findOperationsByUserIdAndDateBetween(UUID userId,
                                                         LocalDate dateStart,
                                                         LocalDate dateEnd);

    @Query("SELECT o " +
            "FROM Operation o " +
            "WHERE o.user.id =:userId " +
            "AND o.date BETWEEN :dateStart AND :dateEnd " +
            "AND o.isIncome =:isIncome")
    List<Operation> findOperationsByUserIdAndIncomeAndDateBetween(UUID userId,
                                                                  LocalDate dateStart,
                                                                  LocalDate dateEnd,
                                                                  boolean isIncome);

//    List<Operation> findAllByUserIdAndIncomeIsAndDateBetween(UUID userId,
//                                                           boolean is_income,
//                                                           LocalDate dateStart,
//                                                           LocalDate dateEnd);

    //TODO: проверить методы
//    List<Operation> findByUserAndDateBetweenAndIsIncome(UUID userId, LocalDate start, LocalDate end, boolean isIncome);
}
