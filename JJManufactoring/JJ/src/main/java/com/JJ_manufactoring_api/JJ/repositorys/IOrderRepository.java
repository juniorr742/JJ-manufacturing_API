package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOrderRepository extends JpaRepository<Order, UUID> {
}
