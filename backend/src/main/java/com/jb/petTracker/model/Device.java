package com.jb.petTracker.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "devices")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Device {
	private String id;
	private String name;

}