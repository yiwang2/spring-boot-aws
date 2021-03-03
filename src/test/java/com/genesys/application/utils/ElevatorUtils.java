package com.genesys.application.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesys.application.model.Elevator;
import com.genesys.application.model.ElevatorState;
import com.genesys.application.model.dto.ElevatorStatusDTO;

public class ElevatorUtils {

	
	public static Elevator createElevator(String elevatorId, List<Integer> floorIds) {
		Elevator elevator = new Elevator();
		elevator.setId(elevatorId);
		elevator.setFloorIds(floorIds);
		return elevator;
	}
	
	public static Elevator createElevator (String elevatorId, Integer currentFloorId, ElevatorState currentState) {
		Elevator elevator = new Elevator();
		elevator.setId(elevatorId);
		elevator.setCurrentFloorId(currentFloorId);
		elevator.setState(currentState);
		return elevator;
	}
	
	public static Elevator createElevator(String elevatorId, ElevatorState state) {
		Elevator elevatorTest = new Elevator();
		elevatorTest.setId(elevatorId);
		elevatorTest.setState(state);

		return elevatorTest;
	}
	
	public static String createElevatorStatusDTOJson (String id, 
			ElevatorState state, Integer currentFloorId) throws JsonProcessingException {
		ElevatorStatusDTO elevatorStatusDTO = new ElevatorStatusDTO(id, state, currentFloorId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(elevatorStatusDTO);
	}
}
