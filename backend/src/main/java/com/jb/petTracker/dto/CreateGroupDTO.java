package com.jb.petTracker.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupDTO {
	private String name;
	private List<DeviceLocationsDTO> deviceLocations;
}
