package com.jb.petTracker.model;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.data.geo.Point;

import com.jb.petTracker.dto.TraccarLocationDTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Location {
	@Id
	private String id;
	private Point coordinates;
	private LocalTime timestamp;

	public Location(TraccarLocationDTO traccarLocation) {
		super();
		this.coordinates = new Point(traccarLocation.getLat(), traccarLocation.getLon());
		this.timestamp = Instant.ofEpochMilli(traccarLocation.getTimestamp()).atZone(ZoneId.of("UTC")).toLocalTime();
	}
}
