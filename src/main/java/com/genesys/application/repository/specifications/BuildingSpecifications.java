package com.genesys.application.repository.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.genesys.application.model.Building;

/**
 * Use specification to query once instead of implementing findById some many times
 *
 */
public class BuildingSpecifications {

	/**
	 * @param buildingIds
	 * @return Specification with predicate to filter buildings whose id in the list
	 */
	public static Specification<Building> searchBuildingWithIds(List<String> buildingIds) {
		
		//this applies Specification -> 
		//Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);
		return  (building, cq, cb) -> building.get("id").in(buildingIds);
	}
}
