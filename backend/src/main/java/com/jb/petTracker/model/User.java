package com.jb.petTracker.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
	@Id
	private ObjectId id;
	private String username;
	private String email;
	private String password;
	private String roles;
	private List<Device> devices = new ArrayList<>();
}