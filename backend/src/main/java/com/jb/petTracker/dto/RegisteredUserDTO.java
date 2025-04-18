package com.jb.petTracker.dto;

import org.bson.types.ObjectId;

import com.jb.petTracker.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserDTO {

	private ObjectId id;
	private String username;
	private String email;

	public RegisteredUserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}
}
