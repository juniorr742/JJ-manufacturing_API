package com.JJ_manufactoring_api.JJ.repositorys;

import com.JJ_manufactoring_api.JJ.model.Client;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.UUID;

public interface IClientRepository extends JpaRepositoryImplementation<Client, UUID> {
}
