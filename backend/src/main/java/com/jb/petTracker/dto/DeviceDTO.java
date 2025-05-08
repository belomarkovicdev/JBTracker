package com.jb.petTracker.dto;

import org.bson.types.ObjectId;

import com.jb.petTracker.model.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
	private ObjectId id;
	private String deviceId;
	private String name;

	public DeviceDTO(Device device) {
		this.id = device.getId();
		this.deviceId = device.getDeviceId();
		this.name = device.getName();
	}
}
