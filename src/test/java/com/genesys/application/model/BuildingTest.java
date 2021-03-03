package com.genesys.application.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import com.genesys.application.utils.ListUtils;

@RunWith(SpringRunner.class)
public class BuildingTest {

	/**
	 * Initialise the building by constructor and make sure the getters could return expected value
	 * @throws Exception
	 */
	@Test
	public void testBuildingInitialization () throws Exception {
		String buildingName = "Building 1";
		String buildingLocation = "Cork";
		List<String> elevatorIds = new ArrayList<>(Arrays.asList("elevator 1", "elevator 2"));
		Building buildingTest = new Building(buildingName, buildingLocation, elevatorIds);
		
		assertThat(buildingTest.getName().equals(buildingName));
		assertThat(buildingTest.getLocation() == buildingLocation);
		assertThat(ListUtils.ifTwoStringListsAreIdentical(elevatorIds, buildingTest.getElevatorIds()));
		
		
	}
}
