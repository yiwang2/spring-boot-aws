package com.genesys.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;


/**
 * @id the random generated integer id by Random.nextInt()
 * @description description of the floor such as 1st floor, basement
 */
@Entity
@Table(name = "floor")
public class Floor {

	@Id
	@GeneratedValue(generator = "randomIntegerId")
	@GenericGenerator(name = "randomIntegerId", strategy = "com.genesys.application.utils.RandomIntegerIdGenerator")
	private Integer id;
	@NotNull (message = "The floor's description shall not be null")
	private String description;
	
	public Floor () {
		
	}

    
	public Floor(Integer id, @NotNull(message = "The floor's description shall not be null") String description) {
		this(description);
		this.id = id;
	}



	public Floor(@NotNull String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Floor [id=" + id + ", description=" + description + "]";
	}
	
	
}
