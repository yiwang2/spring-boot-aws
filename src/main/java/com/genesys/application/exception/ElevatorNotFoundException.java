package com.genesys.application.exception;

public class ElevatorNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2844372732537682428L;

	public ElevatorNotFoundException(String id) {
		super("Could not find elevator by id " + id);
	}
}
