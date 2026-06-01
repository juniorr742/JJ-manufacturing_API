package com.JJ_manufactoring_api.JJ.model;

import com.JJ_manufactoring_api.JJ.model.ENUM.StatusOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    private int quantity;

    @Column(name = "starter_date", nullable = true)
    private LocalDate starterDate;

    @NotNull
    @Column(name = "expected_delivery_date")
    private LocalDate expectedDeliveryDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusOrder statusPedido;
}
