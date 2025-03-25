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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "device_locations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DeviceLocations {

	@Id
	private ObjectId id;
	@Indexed
	private String deviceId;
	private LocalDate date;
	private List<Location> locations = new ArrayList<>();
	
	public DeviceLocations(ReceiveLocationDTO traccarLocationDTO){
		this.deviceId = traccarLocationDTO.getId();
		this.locations.add(new Location(traccarLocationDTO));
		this.date = LocalDate.now();
	}
}
