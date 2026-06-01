package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.Financial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFinancialRepository extends JpaRepository<Financial, UUID> {

}
