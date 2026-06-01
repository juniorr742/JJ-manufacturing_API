package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID> {
}
