package com.jb.petTracker.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "groups")
@Data @AllArgsConstructor @NoArgsConstructor
public class Group {
	@Id
	private ObjectId id;
	private String name;
	@DBRef
	private List<DeviceLocations> deviceTrackingSessions;
}
