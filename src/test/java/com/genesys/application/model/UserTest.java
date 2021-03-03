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
public class UserTest {

	/**
	 * Initialise the user by constructor and make sure the getters could return expected value
	 * @throws Exception
	 */
	@Test
	public void testUserInitialization () throws Exception {
		String userName = "Test user";
		List<String> buildingIds = new ArrayList<>(Arrays.asList("Building 1", "Building 2"));
		User userTest = new User(userName, buildingIds);
		
		assertThat(userTest.getName().equals(userName));
		assertThat(ListUtils.ifTwoStringListsAreIdentical(buildingIds, userTest.getBuildingIds()));
	}
}
