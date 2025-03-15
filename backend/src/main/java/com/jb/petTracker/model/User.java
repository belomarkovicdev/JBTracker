package com.jb.petTracker.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	private String id;
	private String username;
	private List<String> deviceIds;

	public User() {
		super();
	}

	public User(String id, String username, List<String> deviceIds) {
		super();
		this.id = id;
		this.username = username;
		this.deviceIds = deviceIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(List<String> deviceIds) {
		this.deviceIds = deviceIds;
	}

}
