package com.genesys.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.application.model.Building;
import com.genesys.application.model.User;
import com.genesys.application.model.dto.UserUpdateDTO;
import com.genesys.application.service.BuildingService;
import com.genesys.application.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BuildingService buildingService;

	@GetMapping("/user")
	public List<User> getUsers () {
		return userService.findAllUsers();
	}
	
	/**
	 * @param id
	 * @return List<Building> or [] - all buildings where user could access into if
	 *         buildingId != null, then filter the expected one
	 */
	@GetMapping("/user/{id}/building")
	public List<Building> getUserBuildings(@NotBlank @PathVariable String id,
			@RequestParam(value = "buildingId", required = false) String buildingId) {
		User existingUser = userService.findUserById(id);
		List<String> buildingIds;
		if (buildingId != null) {
			buildingIds = new ArrayList<>();
			buildingIds.add(buildingId);
		} else {
			buildingIds = existingUser.getBuildingIds();
		}
		return this.buildingService.findBuildingsByIds(buildingIds);
	}

	/**
	 * @param user
	 * @return String - user id
	 * 
	 *         As the new user is added and the user id is auto generated, the
	 *         returned user id will help to address the new user
	 */
	@PostMapping("/user")
	public String addNewUser(@Valid @RequestBody User user) {
		return this.userService.saveUser(user);
	}

	/**
	 * @param user dto - user with updated data
	 * @param id   - user id
	 * @return String user id 
	 * Update user name or building ids
	 */
	@PutMapping("/user/{id}")
	public String updateUserBuildingInfo(@Valid @RequestBody UserUpdateDTO user, @NotBlank @PathVariable String id) {
		User existingUser = userService.findUserById(id);
		if (user.getName() != null) {
			existingUser.setName(user.getName());
		}
		
		if (user.getBuildingIds() != null) {
			existingUser.setBuildingIds(user.getBuildingIds());
		}
		
		return this.userService.saveUser(existingUser);
	}
}
