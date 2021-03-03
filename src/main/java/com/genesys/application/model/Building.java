package com.genesys.application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * @id the random generated String id by UUUID
 * @name name of the building
 * @location the building location
 * @elevatorIds list of elevators the building has
 */
@Entity
@Table(name = "building")
public class Building {

	@Id
	@GeneratedValue(generator = "randomBuildingId")
	@GenericGenerator(name = "randomBuildingId", strategy = "com.genesys.application.utils.RandomStringIdGenerator")
	private String id;
	@NotNull (message = "The building's name shall not be null")
	@NotEmpty (message = "The building's name shall not be empty")
	private String name;
	@NotNull (message = "The building's location shall not be null")
	@NotEmpty (message = "The building's location shall not be empty")
	private String location;
	@NotNull
	@ElementCollection
	private List<String> elevatorIds = new ArrayList<>();
	
	public Building () {
		
	}

	public Building(@NotNull String name, @NotNull String location, @NotNull List<String> elevatorIds) {
		this.name = name;
		this.location = location;
		this.elevatorIds = elevatorIds;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getElevatorIds() {
		return elevatorIds;
	}

	public void setElevatorIds(List<String> elevatorIds) {
		this.elevatorIds = elevatorIds;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + ", location=" + location + ", elevatorIds=" + String.join(",", elevatorIds)
				+ "]";
	}
	
	
	
}
