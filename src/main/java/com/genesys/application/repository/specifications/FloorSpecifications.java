package com.genesys.application.repository.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.genesys.application.model.Floor;

/**
 * Use specification to query once instead of implementing findById some many times
 *
 */
public class FloorSpecifications {

	/**
	 * @param floorIds
	 * @return Specification with predicate to filter floor whose id in the list
	 */
	public static Specification<Floor> searchFloorWithIds(List<Integer> floorIds) {
		
		//this applies Specification -> 
		//Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);
		return  (floor, cq, cb) -> floor.get("id").in(floorIds);
	}
}
