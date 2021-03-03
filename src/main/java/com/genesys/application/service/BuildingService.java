package com.genesys.application.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.application.exception.BuildingNotFoundException;
import com.genesys.application.model.Building;
import com.genesys.application.repository.BuildingRepository;
import com.genesys.application.repository.specifications.BuildingSpecifications;

@Service
public class BuildingService {

	@Autowired
    private BuildingRepository buildingRepository;
	
	/**
	 * @param buildings
	 * @return buildings' ids after saving into database successfully
	 * 
	 * The saving result of buildings shall not return a heavy list
	 * The buildings' ids will help API consumer target the buildings if it is necessary
	 */
	public List<String> saveBuildings (List<Building> buildings) {
		Iterable<Building> saveBuildingsResult = this.buildingRepository.saveAll(buildings);
		return StreamSupport.stream(saveBuildingsResult.spliterator(), false).map(Building::getId).collect(Collectors.toList());
	}
	
	
	public void deleteAllBuildings () {
		this.buildingRepository.deleteAll();
	}
	
	
	/**
	 * @param buildingIds
	 * @return List<Buildings>
	 * 
	 * any building whose id is in buildingIds will be included 
	 */
	public List<Building> findBuildingsByIds (List<String> buildingIds) {
		return this.buildingRepository.findAll(BuildingSpecifications.searchBuildingWithIds(buildingIds));
	}
	
	public List<Building> findAllBuildings() {
		return this.buildingRepository.findAll();
	}
	
	
	public Building findBuildingById (String id) {
		return this.buildingRepository.findById(id)
				.orElseThrow(() -> new BuildingNotFoundException(id));
	}
}
