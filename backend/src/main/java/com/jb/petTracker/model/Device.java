package com.jb.petTracker.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jb.petTracker.dto.SaveDeviceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "devices")
@Data @NoArgsConstructor @AllArgsConstructor
public class Device {
	@Id
	private ObjectId id = new ObjectId();
	private String deviceId;
	private String name;
	
	public Device(SaveDeviceDTO deviceDTO) {
		this.name = deviceDTO.getName();
		this.deviceId = deviceDTO.getDeviceId();
	}
}