package com.jb.petTracker.model;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.data.geo.Point;

import com.jb.petTracker.dto.TraccarLocationDTO;

public abstract class Location {
	private String id;
	private String deviceId;
	private Point coordinates;
	private LocalTime timestamp;

	public Location() {
		super();
	}

	public Location(String id, String deviceId, Point coordinates, LocalTime timestamp) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.coordinates = coordinates;
		this.timestamp = timestamp;
	}

	public Location(TraccarLocationDTO traccarLocation) {
		super();
		this.id = traccarLocation.getId();
		this.deviceId = traccarLocation.getId();
		this.coordinates = new Point(traccarLocation.getLat(), traccarLocation.getLon());
		this.timestamp = Instant.ofEpochMilli(traccarLocation.getTimestamp()).atZone(ZoneId.of("UTC")).toLocalTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", deviceId=" + deviceId + ", coordinates=" + coordinates + ", timestamp="
				+ timestamp + "]";
	}
}
