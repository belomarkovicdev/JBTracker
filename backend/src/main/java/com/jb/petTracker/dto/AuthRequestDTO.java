package com.jb.petTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString @Data @AllArgsConstructor @NoArgsConstructor
public class AuthRequestDTO {

	private String username;
	private String password;
}
