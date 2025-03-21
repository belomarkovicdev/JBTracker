package com.jb.petTracker.model;

import com.jb.petTracker.dto.SaveDeviceDTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Device {
	@Id
	private String id;
	private String name;

	public Device(SaveDeviceDTO device) {
		this.name = device.getName();
	}
}