package com.genesys.application.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.application.model.Elevator;
import com.genesys.application.model.Floor;
import com.genesys.application.model.dto.ElevatorStatusDTO;
import com.genesys.application.service.ElevatorService;
import com.genesys.application.service.FloorService;
import com.genesys.application.utils.ModelMapperUtil;

@RestController
public class ElevatorController {

	@Autowired
	private ElevatorService elevatorService;
	
	@Autowired
	private FloorService floorService;
 
	/**
	 * @param id
	 * @return list of floors
	 * For user selecting a floor to go while in elevator
	 */
	@GetMapping("/elevator/{id}/floor")
	public List<Floor> getAvaliableFloors(@NotBlank @PathVariable String id) {
		Elevator elevator = elevatorService.findElevatorById(id);
		List<Integer> floorIds = elevator.getFloorIds();
		return this.floorService.findFloorByIds(floorIds);
	}
	

	/**
	 * @param elevatorStatus
	 * @param id
	 * @return ElevatorStatusDTO - the updated of elevator status
	 * 
	 * operate an elevator means change the state of the elevator and make it goto expected current floor
	 * For summon a elevator, the currentFloorId will be changed to where user is waiting for
	 * For select a floor, the currentFloorId will be changed to what is selected. 
	 * By GET /elevator/{id}/floor to know what floors we can go
	 */
	@PutMapping("/elevator/{id}")
	public ElevatorStatusDTO operateAnElevator(@Valid @RequestBody ElevatorStatusDTO elevatorStatus, 
			                     @NotBlank @PathVariable String id) {

		Elevator elevator = elevatorService.findElevatorById(id);
		elevator.setCurrentFloorId (elevatorStatus.getCurrentFloorId());
		elevator.setState(elevatorStatus.getState());
		
		return ModelMapperUtil.mapDTO(elevatorService.saveElevator(elevator), ElevatorStatusDTO.class);
	}
}
