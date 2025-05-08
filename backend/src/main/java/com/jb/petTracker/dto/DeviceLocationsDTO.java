package com.jb.petTracker.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceLocationsDTO {
	private ObjectId id;
	private String deviceId;
	private LocalDate date;
	private List<LocationDTO> locations;

	public DeviceLocationsDTO(DeviceLocations deviceLocations) {
		super();
		this.id = deviceLocations.getId();
		this.deviceId = deviceLocations.getDeviceId();
		this.date = deviceLocations.getDate();
		this.locations = hasLocations(deviceLocations) ? mapToDTO(deviceLocations.getLocations()) : new ArrayList<>();
	}

	private List<LocationDTO> mapToDTO(List<Location> locations) {
		return locations.stream().map(location -> new LocationDTO(location)).toList();

	}

	private boolean hasLocations(DeviceLocations deviceLocations) {
		return deviceLocations != null && deviceLocations.getLocations() != null
				&& !deviceLocations.getLocations().isEmpty();
	}

}
