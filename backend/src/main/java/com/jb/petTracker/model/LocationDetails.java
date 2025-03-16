package com.jb.petTracker.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jb.petTracker.dto.TraccarLocationDTO;

@Document(collection = "locations")
@CompoundIndexes({ @CompoundIndex(name = "deviceId_timestamp_idx", def = "{'deviceId': 1, 'timestamp': -1}") })
public class LocationDetails extends Location {

	private double accuracy;
	private String batt;

	public LocationDetails() {
		super();
	}

	public LocationDetails(double accuracy, String batt) {
		super();
		this.accuracy = accuracy;
		this.batt = batt;
	}

	public LocationDetails(TraccarLocationDTO traccarLocation) {
		super(traccarLocation);
		this.accuracy = traccarLocation.getAccuracy();
		this.batt = traccarLocation.getBatt();
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
		return super.toString() + "LocationDetails [accuracy=" + accuracy + ", batt=" + batt + "]";
	}

}
