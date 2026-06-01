package com.JJ_manufactoring_api.JJ.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "machine_maintenence")
@NoArgsConstructor
public class MachineMaintenence {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @NotNull
    @Column(name = "maintenence_date")
    private LocalDate maintenenceDate;

    @NotBlank
    @Column(name = "mechanic_name")
    @Max(45)
    private String mechanicName;
}
