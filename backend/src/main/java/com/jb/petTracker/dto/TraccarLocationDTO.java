package com.jb.petTracker.dto;

public class TraccarLocationDTO {
	private String id;
	private double lat;
	private double lon;
	private long timestamp;
	private double accuracy;
	private String batt;

	public TraccarLocationDTO(String id, double lat, double lon, long timestamp, double accuracy, String batt) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.timestamp = timestamp;
		this.accuracy = accuracy;
		this.batt = batt;
	}

	public TraccarLocationDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
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

}
