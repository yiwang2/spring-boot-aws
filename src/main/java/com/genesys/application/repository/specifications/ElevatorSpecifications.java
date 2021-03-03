package com.genesys.application.repository.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.genesys.application.model.Elevator;

/**
 * Use specification to query once instead of implementing findById some many times
 *
 */
public class ElevatorSpecifications {

	/**
	 * @param elevatorIds
	 * @return Specification with predicate to filter elevator whose id in the list
	 */
	public static Specification<Elevator> searchElevatorWithIds(List<String> elevatorIds) {
		
		//this applies Specification -> 
		//Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);
		return  (elevator, cq, cb) -> elevator.get("id").in(elevatorIds);
	}
}
