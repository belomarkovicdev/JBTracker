package com.jb.petTracker.model;

import java.time.Instant;

import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jb.petTracker.dto.ReceiveLocationDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
	private String id; // deviceId
	private Point coordinates;
	private LocalTime timestamp;
	private double accuracy;
	private String battery;

	public Location(ReceiveLocationDTO receivedLocation) {
		super();
		this.id = receivedLocation.getId();
		this.coordinates = new Point(receivedLocation.getLat(), receivedLocation.getLon());
		this.timestamp = Instant.ofEpochMilli(receivedLocation.getTimestamp()).atZone(ZoneId.of("UTC")).toLocalTime();
		this.accuracy = receivedLocation.getAccuracy();
		this.battery = "0";
	}
}