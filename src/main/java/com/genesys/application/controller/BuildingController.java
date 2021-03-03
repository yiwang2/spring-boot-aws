package com.genesys.application.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.application.model.Building;
import com.genesys.application.model.Elevator;
import com.genesys.application.model.dto.ElevatorStatusDTO;
import com.genesys.application.service.BuildingService;
import com.genesys.application.service.ElevatorService;
import com.genesys.application.utils.ModelMapperUtil;

@RestController
public class BuildingController {

	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private ElevatorService elevatorService;
	

	/**
	 * @param id - could be got from GET /user/{id}/building
	 * @return List<ElevatorStatusDTO> - list of {id: XXXX, state: XXXX, currentFloorId: XXX}
	 *
	 */
	@GetMapping("/building/{id}/elevator-status")
	public List<ElevatorStatusDTO> getElevatorStatus (@NotBlank @PathVariable String id) {
		Building building = buildingService.findBuildingById(id);
		List<String> elevatorIds = building.getElevatorIds();
		List<Elevator> elevators = elevatorService.findElevatorsByIds(elevatorIds);
		return ModelMapperUtil.mapList(elevators, ElevatorStatusDTO.class);
	}
	
	/**
	 * @return all buildings
	 */
	@GetMapping("/building")
	public List<Building> getBuildings () {
		return buildingService.findAllBuildings();
	}
}
