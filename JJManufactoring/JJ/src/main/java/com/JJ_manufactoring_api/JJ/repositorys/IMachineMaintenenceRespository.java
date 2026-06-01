package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.MachineMaintenence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMachineMaintenenceRespository extends JpaRepository<MachineMaintenence, UUID> {

}
