package com.JJ_manufactoring_api.JJ.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "financial")
@NoArgsConstructor
public class Financial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "daily_production_id")
    private DailyProduction dailyProduction;

    @NotNull
    @Column(name = "quantity_delivered")
    private int quantityDelivered;

    @NotNull
    @Column(name = "total_billed")
    private BigDecimal totalBilled;
}
