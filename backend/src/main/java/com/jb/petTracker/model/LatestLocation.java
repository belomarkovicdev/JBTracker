package com.jb.petTracker.model;

import java.io.Serializable;

public class LatestLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long deviceId;
	private double latitude;
	private double longitude;
	private float speed;
	private long timestamp;

	public LatestLocation() {
		super();
	}

	public LatestLocation(Long deviceId, double latitude, double longitude, float speed) {
		this.deviceId = deviceId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.speed = speed;
		this.timestamp = System.currentTimeMillis();
	}

	public LatestLocation(Location location) {
		this.deviceId = location.getId();
		this.latitude = location.getLocation().getX();
		this.longitude = location.getLocation().getY();
		this.timestamp = location.getTimestamp();
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
