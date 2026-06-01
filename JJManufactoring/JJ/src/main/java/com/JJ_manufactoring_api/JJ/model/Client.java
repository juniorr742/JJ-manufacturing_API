package com.JJ_manufactoring_api.JJ.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "client")
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Max(255)
    private String name;

    @NotBlank
    @Max(155)
    private String address;

    @NotBlank
    @Max(13)
    private String cellphone;

    @NotBlank
    @Column(name = "email", unique = true)
    @Max(155)
    private String email;

    @NotBlank
    @Column(name = "cnpj", unique = true)
    @Max(15)
    private String cnpj;
}
