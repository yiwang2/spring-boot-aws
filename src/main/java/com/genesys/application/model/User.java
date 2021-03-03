package com.genesys.application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;


/**
 * @id the random generated String user id by UUUID
 * @name any valid user name
 * @buildingIds list of buildings user could access
 *
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(generator = "randomUserId")
	@GenericGenerator(name = "randomUserId", strategy = "com.genesys.application.utils.RandomStringIdGenerator")
	private String id;
	@NotNull (message = "The user's name shall not be null")
	@NotEmpty (message = "The user's name shall not be empty")
	private String name;
	@NotNull
	@ElementCollection
	private List<String> buildingIds = new ArrayList<>();
	
	public User () {
		
	}
	

	public User(@NotNull String name, @NotNull List<String> buildingIds) {
		super();
		this.name = name;
		this.buildingIds = buildingIds;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<String> buildingIds) {
		this.buildingIds = buildingIds;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", buildingIds=" + String.join(",", buildingIds) + "]";
	}
	
	
}
