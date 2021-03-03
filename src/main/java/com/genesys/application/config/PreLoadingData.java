package com.genesys.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.genesys.application.model.Building;
import com.genesys.application.model.Elevator;
import com.genesys.application.model.ElevatorState;
import com.genesys.application.model.Floor;
import com.genesys.application.model.User;
import com.genesys.application.service.BuildingService;
import com.genesys.application.service.ElevatorService;
import com.genesys.application.service.FloorService;
import com.genesys.application.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate the data for initialisation 
 *
 */
@Component
public class PreLoadingData implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(PreLoadingData.class);
	
	@Autowired
    private FloorService floorService;
	
	@Autowired
    private ElevatorService elevatorService;
	
	@Autowired
    private BuildingService buildingService;
	
	@Autowired
    private UserService userService;

	/**
	 * Assuming we have 3 floors, 2 elevators in each building
	 * Create one building for existing user
	 * Assuming we have 1 user in very beginning
	 * And and another building for search purpose
	 * Create test data and save into database
	 */
	@Override
	public void run(String... args) throws Exception {
		//step 1: clean all repositories in very beginning
		this.cleanAllRepositories();
		//step 2: create a new user
		this.addUser(this.createNewUser("Test user", this.createAllBuildingInformation ("Cork", "Building 1", "Elevator 1", "Elevator 2")));
		//step 3: create another new building for search purpose
		this.createAllBuildingInformation ("Dublin", "Building 2", "Elevator 3", "Elevator 4");
	}
	
	private void cleanAllRepositories () {
		
		logger.info("Clean all repositories");
		
		this.floorService.deleteAllFloors();
		this.elevatorService.deleteAllElevators();
		this.buildingService.deleteAllBuildings();
		this.userService.deleteAllUsers();
	}
	
	/**
	 * @return List<String> buildingIds
	 * create the whole building informaiton and return building ids for user
	 */
	private List<String> createAllBuildingInformation (String location, String buildingName, 
			                                   String elevatorOne, String elevatorTwo) {
		List<Integer> floorIds = this.addFloors(this.createNewFloors());
		//save new elevators
		List<Elevator> elevators = new ArrayList<>();
		elevators.add(this.createNewElevator(elevatorOne, floorIds, floorIds.get(0), ElevatorState.STOPPED));
		elevators.add(this.createNewElevator(elevatorTwo, floorIds, floorIds.get(0), ElevatorState.STOPPED));
		List<String> elevatorIds = this.addElevators(elevators);
		//save new buildings
		List<Building> buildings = new ArrayList<>();
		buildings.add(this.createNewBuilding(buildingName, location, elevatorIds));
		return this.addBuildings(buildings);
	}
	
    private List<Floor> createNewFloors () {
    	List<Floor> newFloors = new ArrayList<Floor>();
    	Floor floorBasement = new Floor ("Basement");
    	newFloors.add(floorBasement);
    	Floor floorZero = new Floor ("Ground floor");
    	newFloors.add(floorZero);
    	Floor floorFirst = new Floor ("First floor");
    	newFloors.add(floorFirst);
    	return newFloors;
    }
    
	private List<Integer> addFloors(List<Floor> floors) {
		floors.forEach(floor -> logger.info("Saving "+ floor.toString()));
		return this.floorService.saveFloors(floors);
	}
    
    private Elevator createNewElevator(String name, List<Integer> floorIds, Integer currentFloorId, ElevatorState state) {
    	return new Elevator (name, floorIds, currentFloorId, state);
    }
    
    private List<String> addElevators (List<Elevator> elevators) {
    	elevators.forEach(elevator -> logger.info("Saving "+ elevators.toString()));
    	return this.elevatorService.saveElevators(elevators);
    }
    
    private Building createNewBuilding (String name,String location, List<String> elevatorIds) {
    	return new Building(name, location, elevatorIds);
    }
    
    private List<String> addBuildings (List<Building> buildings) {
    	buildings.forEach(building -> logger.info("Saving "+ building.toString()));
    	return this.buildingService.saveBuildings(buildings);
    }
    
    private User createNewUser (String name, List<String> buildingIds) {
    	return new User(name, buildingIds);
    }
    
    private void addUser (User testUser) {
    	logger.info("Saving "+ testUser.toString());
    	this.userService.saveUser(testUser);
    }

}
