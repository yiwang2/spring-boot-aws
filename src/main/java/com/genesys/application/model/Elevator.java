package com.genesys.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.genesys.application.validator.ElevatorStateSubset;


/**
 *  @id the random generated String id by UUUID
 *  @name name of the elevator
 *  @floorIds: floors the elevator will cover - keep id only
 *  @currentFloorId: where the elevator is at now
 *  @state: the elevator state includes up / down/ stopped /out of service
 */
@Entity
@Table(name = "elevator")
public class Elevator {
	
	@Id
	@GeneratedValue(generator = "randomElevatorId")
	@GenericGenerator(name = "randomElevatorId", strategy = "com.genesys.application.utils.RandomStringIdGenerator")
	private String id;
	@NotNull (message = "The elevator's name shall not be null")
	@NotEmpty (message = "The elevator's name shall not be empty")
	private String name;
	@NotNull
	@ElementCollection
	private List<Integer> floorIds = new ArrayList<>();
	@NotNull (message = "The elevator's current floor shall not be null")
	private Integer currentFloorId;
	
	@NotNull(message = "The elevator's state shall not be null")
	@ElevatorStateSubset(anyOf = {ElevatorState.UP, ElevatorState.DOWN, ElevatorState.STOPPED, ElevatorState.OUT_OF_SERVICE})
	private ElevatorState state;

	public Elevator () {
		
	}

	public Elevator(@NotNull String name, @NotNull List<Integer> floorIds, @NotNull Integer currentFloorId,
			@NotNull ElevatorState state) {
		this.name = name;
		this.floorIds = floorIds;
		this.currentFloorId = currentFloorId;
		this.state = state;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ElevatorState getState() {
		return state;
	}

	public void setState(ElevatorState state) {
		this.state = state;
	}

	public List<Integer> getFloorIds() {
		return floorIds;
	}

	public void setFloorIds(List<Integer> floorIds) {
		this.floorIds = floorIds;
	}

	public Integer getCurrentFloorId() {
		return currentFloorId;
	}

	public void setCurrentFloorId(Integer currentFloorId) {
		this.currentFloorId = currentFloorId;
	}

	@Override
	public String toString() {
		return "Elevator [id=" + id + ", name=" + name + ", floorIds=" + 
				floorIds.stream().map(floorId -> floorId.toString()).collect(Collectors.joining(",")) + ", currentFloorId=" + currentFloorId
				+ ", state=" + state.getCode() + "]";
	}
	
	
	
}
