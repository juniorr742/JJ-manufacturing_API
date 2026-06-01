package com.JJ_manufactoring_api.JJ.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "O preço não pode ser vazio")
    private double price;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String name;

    @NotBlank(message = "O código ncm do produto é obrigatório")
    private String ncm;

    @NotNull(message = "o tempo de produção é obrigatório")
    private int productionTime;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;
}
