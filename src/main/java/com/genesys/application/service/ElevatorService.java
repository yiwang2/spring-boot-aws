package com.genesys.application.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.application.exception.ElevatorNotFoundException;
import com.genesys.application.model.Elevator;
import com.genesys.application.repository.ElevatorRepository;
import com.genesys.application.repository.specifications.ElevatorSpecifications;

@Service
public class ElevatorService {

	@Autowired
	private ElevatorRepository elevatorRepository;

	/**
	 * @param elevators
	 * @return elevators' ids after saving into database successfully
	 * 
	 *         The saving result of elevators shall not return a heavy list The
	 *         elevators' ids will help API consumer target the elevators if it is
	 *         necessary
	 */
	public List<String> saveElevators(List<Elevator> elevators) {
		Iterable<Elevator> saveElevatorsResult = this.elevatorRepository.saveAll(elevators);
		return StreamSupport.stream(saveElevatorsResult.spliterator(), false).map(Elevator::getId)
				.collect(Collectors.toList());
	}

	public void deleteAllElevators() {
		this.elevatorRepository.deleteAll();
	}

	/**
	 * @param elevatorIds
	 * @return List<Elevator>
	 *         any elevator whose id is in elevatorIds will be included
	 */
	public List<Elevator> findElevatorsByIds(List<String> elevatorIds) {
		return this.elevatorRepository.findAll(ElevatorSpecifications.searchElevatorWithIds(elevatorIds));
	}
	
	/**
	 * @param elevatorId
	 * @return the existing elevator or throw ElevatorNotFoundException if not found
	 */
	public Elevator findElevatorById (String elevatorId) {
		return this.elevatorRepository.findById(elevatorId)
				.orElseThrow(() -> new ElevatorNotFoundException(elevatorId));
	}
	
	public Elevator saveElevator (Elevator elevator) {
		return this.elevatorRepository.save(elevator);
	}
}
