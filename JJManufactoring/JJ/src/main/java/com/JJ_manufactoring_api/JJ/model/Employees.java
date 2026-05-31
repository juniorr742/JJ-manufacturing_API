package com.JJ_manufactoring_api.JJ.model;

import com.JJ_manufactoring_api.JJ.model.ENUM.StatusEmployees;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "employees")
@Data
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Column(unique = true)
    private String pis;

    @NotBlank
    @Column(unique = true)
    private String rg;

    @NotBlank
    private String position;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEmployees status;

    @NotNull
    private LocalDate birthDay;

    @ManyToMany(mappedBy = "employess")
    private List<DailyProduction> dailyProductions;

}
