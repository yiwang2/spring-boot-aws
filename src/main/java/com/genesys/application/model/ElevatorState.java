package com.genesys.application.model;

public enum ElevatorState {
	UP("UP"), DOWN("DOWN"), STOPPED("STOPPED"), OUT_OF_SERVICE("OUT_OF_SERVICE");
	
	private String code;

	ElevatorState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
