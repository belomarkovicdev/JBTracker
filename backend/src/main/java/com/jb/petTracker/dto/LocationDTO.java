package com.jb.petTracker.dto;

import java.time.LocalTime;

import org.springframework.data.geo.Point;

import com.jb.petTracker.model.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LocationDTO {
	private String id; //deviceId
	private Point coordinates;
	private LocalTime timestamp;
	
	public LocationDTO(Location location) {
		super();
		this.id = location.getId();
		this.coordinates = location.getCoordinates();
		this.timestamp = location.getTimestamp();
	}
	
	
}
