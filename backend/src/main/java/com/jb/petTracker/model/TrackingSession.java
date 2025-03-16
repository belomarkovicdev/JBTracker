package com.jb.petTracker.model;

import java.util.List;

import jakarta.persistence.Id;

public class TrackingSession {
	@Id
	private String id;
	private List<Device> devices;

	public TrackingSession() {
		super();
	}

	public TrackingSession(String id, List<Device> devices) {
		super();
		this.id = id;
		this.devices = devices;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

}
