package com.jb.petTracker.dto;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.Group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

	private ObjectId id;
	private String name;
	private List<DeviceLocationsDTO> deviceLocations;

	public GroupDTO(Group group) {
		this.id = group.getId();
		this.name = group.getName();
		this.deviceLocations = hasDeviceLocations(group.getDeviceLocations()) ? mapToDTO(group.getDeviceLocations())
				: new ArrayList<>();
	}

	private List<DeviceLocationsDTO> mapToDTO(List<DeviceLocations> deviceLocations) {
		return deviceLocations.stream().map(deviceLocation -> new DeviceLocationsDTO(deviceLocation)).toList();
	}

	private boolean hasDeviceLocations(List<DeviceLocations> deviceLocations) {
		return !deviceLocations.isEmpty() && deviceLocations != null;
	}
}