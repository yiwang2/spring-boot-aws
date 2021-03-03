package com.genesys.application.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.reset;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

import com.genesys.application.model.Building;

import com.genesys.application.model.User;
import com.genesys.application.model.dto.UserSaveDTO;
import com.genesys.application.service.BuildingService;
import com.genesys.application.service.UserService;
import com.genesys.application.utils.BuildingUtils;
import com.genesys.application.utils.UserUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

	@InjectMocks
    private UserController userController;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@MockBean
	private BuildingService buildingService;

	@Test
	public void get_thenReturnUsers() throws Exception {
		// prepare data
		String userId = "42345678";
		User user = UserUtils.createUser(userId);
		List<User> users = new ArrayList<>();
		users.add(user);

		// mock the services
		given(userService.findAllUsers()).willReturn(users);

		// test api
		mvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", is(userId)));

		reset(userService);
	}

	@Test
	public void givenId_thenReturnBuildings() throws Exception {

		// prepare data
		String userId = "94a4e999";
		String buildingId = "94a4e998";
		String[] buildingIds = { buildingId };
		User user = UserUtils.createUser(userId, Arrays.asList(buildingIds));
		List<Building> buildings = new ArrayList<Building>();
		buildings.add(BuildingUtils.createBuilding(buildingId));

		// mock the services
		given(userService.findUserById(userId)).willReturn(user);
		given(buildingService.findBuildingsByIds(Arrays.asList(buildingIds))).willReturn(buildings);

		// test api
		mvc.perform(get("/user/" + userId + "/building").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(buildingId)));

		reset(userService);
		reset(buildingService);
	}

	@Test
	public void givenUser_thenAddUser() throws Exception {
		// prepare data
		String mockUserId = "94a4e999";
		String userName = "test user";
		String buildingId = "94a4e998";
		String[] buildingIds = { buildingId };
		User createUser = UserUtils.createUserByName(userName, Arrays.asList(buildingIds));
		UserSaveDTO savedResult = new UserSaveDTO(mockUserId);
		
		// mock service
		given(userService.saveUser(createUser)).willReturn(savedResult);
		// test api
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(UserUtils.createUserBodyJson(userName, Arrays.asList(buildingIds))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(mockUserId)));
		
		reset(userService);
	}

	@Test
	public void givenUserIdAndUpdateDTO_thenUpdateUser() throws Exception {
		// prepare data
		String mockUserId = "12345678";
		String userName = "test user";
		String newUsername = "new test user";
		String buildingId = "building 1";
		String[] buildingIds = { buildingId };
		User existingUser = UserUtils.createUser(mockUserId, userName, Arrays.asList(buildingIds));
		User updatedUser = UserUtils.createUser(mockUserId, newUsername, Arrays.asList(buildingIds));
		UserSaveDTO savedResult = new UserSaveDTO(mockUserId);
		
		// mock service
		given(userService.findUserById(mockUserId)).willReturn(existingUser);
		given(userService.saveUser(updatedUser)).willReturn(savedResult);
		
		mvc.perform(put("/user/"+mockUserId).contentType(MediaType.APPLICATION_JSON)
				.content(UserUtils.createUserBodyJson(newUsername, Arrays.asList(buildingIds))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(mockUserId)));
		
		reset(userService);
	}

}
