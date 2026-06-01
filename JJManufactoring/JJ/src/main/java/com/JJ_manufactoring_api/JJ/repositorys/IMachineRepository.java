package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMachineRepository extends JpaRepository<Machine, UUID> {

}
