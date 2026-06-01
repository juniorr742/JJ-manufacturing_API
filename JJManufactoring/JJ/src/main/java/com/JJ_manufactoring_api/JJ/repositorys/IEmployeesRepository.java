package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEmployeesRepository extends JpaRepository<Employees, UUID> {

}
