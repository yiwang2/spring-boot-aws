package com.genesys.application.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.genesys.application.model.Building;
import com.genesys.application.model.Elevator;
import com.genesys.application.model.ElevatorState;
import com.genesys.application.service.BuildingService;
import com.genesys.application.service.ElevatorService;
import com.genesys.application.utils.BuildingUtils;
import com.genesys.application.utils.ElevatorUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BuildingController.class)
public class BuildingControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BuildingService buildingService;

	@MockBean
	private ElevatorService elevatorService;

	@Test
	public void get_thenReturnBuildings() throws Exception {
		// prepare data
		String buildingId = "test building";
		String buildingName = "building 1";
		String location = "somewhere";
		String[] elevatorIdArray = { "1", "2", "3" };
		Building buildingTest = BuildingUtils.createBuilding(buildingId, buildingName, location, elevatorIdArray);
		List<Building> buildings = new ArrayList<>();
		buildings.add(buildingTest);

		// mock service
		given(buildingService.findAllBuildings()).willReturn(buildings);

		// implement the test
		mvc.perform(get("/building").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", is(buildings.get(0).getId())));

		reset(buildingService);
	}

	@Test
	public void givenBuildId_thenReturnElevatorStatus() throws Exception {
		// prepare data
		String buildingId = "test building";
		String elevatorId = "1";
		String buildingName = "building 1";
		String location = "somewhere";
		String[] elevatorIdArray = { elevatorId };
		Building buildingTest = BuildingUtils.createBuilding(buildingId, buildingName, location, elevatorIdArray);

		ElevatorState state = ElevatorState.STOPPED;
		Elevator elevatorTest = ElevatorUtils.createElevator(elevatorId, state);

		List<String> elevatorIds = new ArrayList<>();
		elevatorIds.add(elevatorId);
		List<Elevator> elevators = new ArrayList<>();
		elevators.add(elevatorTest);

		// mock service
		given(buildingService.findBuildingById(buildingId)).willReturn(buildingTest);
		given(elevatorService.findElevatorsByIds(elevatorIds)).willReturn(elevators);

		// implement the test
		mvc.perform(get("/building/" + buildingId + "/elevator-status").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(elevatorIdArray.length)))
				.andExpect(jsonPath("$[0].id", is(elevators.get(0).getId())))
				.andExpect(jsonPath("$[0].state", is(elevators.get(0).getState().getCode())));

		reset(buildingService);
		reset(elevatorService);
	}

	
}
