package com.example.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "is_income", nullable = false)
    private boolean isIncome;

    @Column(name = "operation_date", nullable = false)
    private LocalDate date;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
