package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.DailyProduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDailyProductionRepository extends JpaRepository<DailyProduction, UUID> {
}
