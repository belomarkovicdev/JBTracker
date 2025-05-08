package com.jb.petTracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jb.petTracker.dto.ReceiveLocationDTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "device_locations")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class DeviceLocations {

	@Id
	private ObjectId id;
	@Indexed
	private String deviceId;
	private LocalDate date;
	private List<Location> locations;
	
	public DeviceLocations(ReceiveLocationDTO receiveLocationDTO){
		this.deviceId = receiveLocationDTO.getId();
		this.locations.add(new Location(receiveLocationDTO));
		this.date = LocalDate.now();
	}
	public DeviceLocations(String deviceId) {
		this.deviceId = deviceId;
		this.date = LocalDate.now();
		this.locations = new ArrayList<>();
	}
}
