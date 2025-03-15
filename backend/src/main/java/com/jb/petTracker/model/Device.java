package com.jb.petTracker.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "devices")
public class Device {
	private String id;
	private String name;
	private String userId;
	private List<String> locationIds;

	public Device() {
		super();
	}

	public Device(String id, String name, String userId, List<String> locationIds) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.locationIds = locationIds;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(List<String> locationIds) {
		this.locationIds = locationIds;
	}

}
