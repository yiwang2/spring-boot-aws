package com.genesys.application.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.reset;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.genesys.application.model.Elevator;
import com.genesys.application.model.ElevatorState;
import com.genesys.application.model.Floor;
import com.genesys.application.service.ElevatorService;
import com.genesys.application.service.FloorService;
import com.genesys.application.utils.ElevatorUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ElevatorController.class)
public class ElevatorControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ElevatorService elevatorService;

	@MockBean
	private FloorService floorService;

	/**
	 * Test: GET /elevator/{id}/floor
	 * @throws Exception
	 */
	@Test
	public void givenElevatorId_thenReturnFloors() throws Exception {
		String elevatorId = "testElevatorId";
		List<Floor> floors = this.createFloors();
		Integer[] floorIds = new Integer[] { 1, 2, 3 };
		Elevator elevator = ElevatorUtils.createElevator(elevatorId, Arrays.asList(floorIds));

		given(elevatorService.findElevatorById(elevatorId)).willReturn(elevator);
		given(floorService.findFloorByIds(Arrays.asList(floorIds))).willReturn(floors);

		mvc.perform(get("/elevator/" + elevatorId + "/floor").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", is(floors.get(0).getId())));

		reset(elevatorService);
		reset(floorService);
	}
	
	/**
	 * Test PUT /elevator/{id}
	 * @throws Exception
	 */
	@Test
	public void givenElevatorID_thenOperateIt () throws Exception{
		String elevatorId = "testElevatorId";
		Integer currentFloorId = Integer.valueOf(-1);
		ElevatorState currentState = ElevatorState.STOPPED;
		Integer newFloorId = Integer.valueOf(0);
		ElevatorState newState = ElevatorState.UP;
		
		//mock the existing elevator
		Elevator elevator = ElevatorUtils.createElevator(elevatorId, currentFloorId, currentState);
		given(elevatorService.findElevatorById(elevatorId)).willReturn(elevator);
		
		//mock the update
		elevator.setState(newState);
		elevator.setCurrentFloorId(currentFloorId);
		given(elevatorService.saveElevator(elevator)).willReturn(elevator);
		
		//validate the result
		mvc.perform(put("/elevator/" + elevatorId)
				           .contentType(MediaType.APPLICATION_JSON_VALUE)
				           .accept(MediaType.APPLICATION_JSON)
				           .content(ElevatorUtils.createElevatorStatusDTOJson(elevatorId, newState, newFloorId)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(elevatorId)))
        .andExpect(jsonPath("$.state", is(newState.getCode())))
        .andExpect(jsonPath("$.currentFloorId", is(newFloorId)));
		
		reset(elevatorService);
	}

	private List<Floor> createFloors() {
		List<Floor> floors = new ArrayList<Floor>();
		Floor floorBasement = new Floor(-1, "Basement");
		floors.add(floorBasement);
		Floor floorZero = new Floor(0, "Ground floor");
		floors.add(floorZero);
		Floor floorFirst = new Floor(1, "First floor");
		floors.add(floorFirst);

		return floors;
	}
}
