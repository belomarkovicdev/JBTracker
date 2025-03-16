package com.jb.petTracker.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jb.petTracker.dto.TraccarLocationDTO;

@Document(collection = "locations")
@CompoundIndexes({ @CompoundIndex(name = "deviceId_timestamp_idx", def = "{'deviceId': 1, 'timestamp': -1}") })
public class LocationDetails extends Location {

	private double accuracy;
	private String battery;

	public LocationDetails() {
		super();
	}

	public LocationDetails(double accuracy, String battery) {
		super();
		this.accuracy = accuracy;
		this.battery = battery;
	}

	public LocationDetails(TraccarLocationDTO traccarLocationDTO) {
		super(traccarLocationDTO);
		this.accuracy = traccarLocationDTO.getAccuracy();
		this.battery = traccarLocationDTO.getBatt();
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	@Override
	public String toString() {
		return super.toString() + "LocationDetails [accuracy=" + accuracy + ", battery=" + battery + "]";
	}

}
