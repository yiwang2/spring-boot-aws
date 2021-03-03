package com.genesys.application.model;

import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class FloorTest {

	/**
	 * Initialise the floor by constructor and make sure the getters could return expected value
	 * @throws Exception
	 */
	@Test
	public void testFloorInitialization () throws Exception{
		Floor testFloor = new Floor("First floor");
		assertThat(testFloor.getDescription().equals("First floor"));
	}
}
