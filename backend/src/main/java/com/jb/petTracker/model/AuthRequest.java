package com.jb.petTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString @Data @AllArgsConstructor @NoArgsConstructor
public class AuthRequest {

	private String username;
	private String password;
}
