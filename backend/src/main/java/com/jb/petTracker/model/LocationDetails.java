package com.jb.petTracker.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.jb.petTracker.dto.ReceiveLocationDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "locations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LocationDetails extends Location {

	private double accuracy;
	private String battery;

	public LocationDetails(ReceiveLocationDTO receiveLocationDTO) {
		super(receiveLocationDTO);
		this.accuracy = receiveLocationDTO.getAccuracy();
		this.battery = "0";
	}
}
