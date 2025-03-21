package com.jb.petTracker.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.jb.petTracker.dto.SaveDeviceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Device {
	@Id
	private String id = new ObjectId().toString();
	private String name;
	
	public Device(SaveDeviceDTO deviceDTO) {
		this.name = deviceDTO.getName();
	}
}