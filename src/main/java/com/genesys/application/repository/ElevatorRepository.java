package com.genesys.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.genesys.application.model.Elevator;

@Repository
public interface ElevatorRepository extends JpaRepository<Elevator, String>, JpaSpecificationExecutor<Elevator>{

}
