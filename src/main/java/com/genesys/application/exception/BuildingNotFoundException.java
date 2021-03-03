package com.genesys.application.exception;

public class BuildingNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -5412102386808867626L;
	
	public BuildingNotFoundException(String id) {
		super("Could not find building by id " + id);
	}

}
