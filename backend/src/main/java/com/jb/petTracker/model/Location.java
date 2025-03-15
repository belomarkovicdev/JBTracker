package com.jb.petTracker.model;

import java.time.LocalTime;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;

@Document(collection = "locations")
public class Location {
	@Id
	private Long id;
	private Point location;
	private LocalTime timestamp;
	private double accuracy;
	private String batt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public String getBatt() {
		return batt;
	}

	public void setBatt(String batt) {
		this.batt = batt;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", location=" + location + ", timestamp=" + timestamp + ", accuracy=" + accuracy
				+ ", batt=" + batt + "]";
	}

	public Location() {
		super();
	}

	public Location(Long id, Point location, double accuracy, String batt) {
		this.id = id;
		this.location = location;
		this.accuracy = accuracy;
		this.batt = batt;
	}

}
