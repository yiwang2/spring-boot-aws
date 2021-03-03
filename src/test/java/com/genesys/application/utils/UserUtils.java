package com.genesys.application.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesys.application.model.User;

public class UserUtils {

	public static User createUser(String userId) {
		User user = new User();
		user.setId(userId);
		return user;
	}
	
	public static User createUser (String userId, List<String> buildingIds) {
		User user = createUser(userId);
		user.setBuildingIds(buildingIds);
		return user;
	}
	
	public static User createUser (String userId, String userName, List<String> buildingIds) {
		User user = createUser(userId);
		user.setName(userName);
		user.setBuildingIds(buildingIds);
		return user;
	}
	
	public static User createUserByName (String userName, List<String> buildingIds) {
		User user = new User();
		user.setName(userName);
		user.setBuildingIds(buildingIds);
		return user;
	}
	
	public static String createUserBodyJson (String userName, List<String> buildingIds) throws JsonProcessingException {
		User user = createUserByName(userName, buildingIds);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(user);
	}
}
