package com.genesys.application.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.genesys.application.utils.ListUtils;

@RunWith(SpringRunner.class)
public class ElevatorTest {
	
	/**
	 * Initialise the elevator by constructor and make sure the getters could return expected value
	 * @throws Exception
	 */
	@Test
	public void testElevatorInitialization () throws Exception{
		String elevatorName = "test elevator";
		Integer currentFloorId = Integer.valueOf(0);
		List<Integer> floorIds = new ArrayList<>(Arrays.asList(0,1,2));
		ElevatorState expectedState = ElevatorState.STOPPED;
		Elevator elevatorTest = new Elevator(elevatorName, floorIds, currentFloorId, expectedState);
		
		assertThat(elevatorTest.getName().equals(elevatorName));
		assertThat(elevatorTest.getCurrentFloorId() == currentFloorId);
		assertThat(elevatorTest.getState() == expectedState);
		assertThat(elevatorTest.getFloorIds().size() == floorIds.size());
		assertThat(ListUtils.ifTwoIntegerListsAreIdentical(floorIds, elevatorTest.getFloorIds()));
	}
}
