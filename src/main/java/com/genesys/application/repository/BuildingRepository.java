package com.genesys.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.genesys.application.model.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, String>, JpaSpecificationExecutor<Building>{

}
