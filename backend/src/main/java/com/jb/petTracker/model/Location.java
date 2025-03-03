package com.jb.petTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Location {
    @Id
	private String id;
    private double lat;
    private double lon;
    private long timestamp;
    private double speed;
    private double bearing;
    private double altitude;
    private double accuracy;
    private String batt;
    
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getBearing() {
		return bearing;
	}
	public void setBearing(double bearing) {
		this.bearing = bearing;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
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
		return "Location [id=" + id + ", lat=" + lat + ", lon=" + lon + ", timestamp=" + timestamp + ", speed=" + speed
				+ ", bearing=" + bearing + ", altitude=" + altitude + ", accuracy=" + accuracy + ", batt=" + batt + "]";
	}
    
    
}
