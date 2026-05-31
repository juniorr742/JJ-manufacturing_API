package com.JJ_manufactoring_api.JJ.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "daily_production")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    @NotNull

    private Order order;
    @Column(name = "current_date")

    @NotNull
    private LocalDate currentDate;

    @Column(name = "expected_production")
    @NotNull
    private int expectedProduction;

    @Column(name = "actual_production")
    private int actualProduction;

    @ManyToMany
    @JoinTable(
        name = "daily_production_employees",
        joinColumns = @JoinColumn(name = "daily_production_id"),
            inverseJoinColumns = @JoinColumn(name = "employees_id")
    )
    private List<Employees> employees;
}
