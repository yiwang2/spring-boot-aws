package com.genesys.application.utils;

import java.util.Arrays;
import java.util.List;

import com.genesys.application.model.Building;

public class BuildingUtils {

	
	public static Building createBuilding(String buildingId, String buildingName, String location,
			String[] elevatorIdArray) {
		List<String> elevatorIds = Arrays.asList(elevatorIdArray);
		Building buildingTest = new Building(buildingName, location, elevatorIds);
		buildingTest.setId(buildingId);
		return buildingTest;
	}
	
	public static Building createBuilding(String buildingId) {
		Building buildingTest = new Building();
		buildingTest.setId(buildingId);
		return buildingTest;
	}
}
