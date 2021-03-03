package com.genesys.application.model.dto;

import javax.validation.constraints.NotNull;

import com.genesys.application.model.ElevatorState;

/**
 * DTO for returning Elevator status including elevator id / state / currentFloorId
 *
 */
public class ElevatorStatusDTO {

	private String id;
	@NotNull(message = "The elevator state shall not be null")
	private ElevatorState state;
	
	@NotNull (message = "The elevator's current floor shall not be null")
	private Integer currentFloorId;
	
	public ElevatorStatusDTO () {
		
	}
	
	public ElevatorStatusDTO(String id, ElevatorState state, Integer currentFloorId) {
		super();
		this.id = id;
		this.state = state;
		this.currentFloorId = currentFloorId;
	}

	public Integer getCurrentFloorId() {
		return currentFloorId;
	}
	
	public void setCurrentFloorId(Integer currentFloorId) {
		this.currentFloorId = currentFloorId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public ElevatorState getState() {
		return state;
	}
	
	public void setState(ElevatorState state) {
		this.state = state;
	}
	
	
}
