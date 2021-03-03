package com.genesys.application.model.dto;

public class UserSaveDTO {

	private String id;
	
	public UserSaveDTO () {
		
	}

	public UserSaveDTO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
