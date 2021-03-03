package com.genesys.application.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.application.model.Floor;
import com.genesys.application.repository.FloorRepository;
import com.genesys.application.repository.specifications.FloorSpecifications;


@Service
public class FloorService {

	@Autowired
    private FloorRepository floorRepository;
	
	
	/**
	 * @param list of floors
	 * @return list of floors' ids after saving into database successfully
	 * 
	 * The saving result of floors shall not return a heavy list
	 * The floors' ids will help API consumer target the floors if it is necessary
	 */
	public List<Integer> saveFloors (List<Floor> floors) {
		Iterable<Floor> saveFloorsResult = this.floorRepository.saveAll(floors);
		return StreamSupport.stream(saveFloorsResult.spliterator(), true).map(Floor::getId).collect(Collectors.toList());
		
	}
	
	public void deleteAllFloors () {
		this.floorRepository.deleteAll();
	}
	
	/**
	 * @param floorIds
	 * @return List<Floor>
	 *         any floor whose id is in floorIds will be included
	 */
	public List<Floor> findFloorByIds(List<Integer> floorIds) {
		return this.floorRepository.findAll(FloorSpecifications.searchFloorWithIds(floorIds));
	}
}
