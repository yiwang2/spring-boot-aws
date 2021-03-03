package com.genesys.application.model.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * DTO for user information update
 *
 */
public class UserUpdateDTO {

	private String name;
	private List<String> buildingIds = new ArrayList<>();
	
	
	public UserUpdateDTO () {
		
	}
	
	public UserUpdateDTO(String name, List<String> buildingIds) {
		super();
		this.name = name;
		this.buildingIds = buildingIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getBuildingIds() {
		return buildingIds;
	}
	public void setBuildingIds(List<String> buildingIds) {
		this.buildingIds = buildingIds;
	}
	
	
}
