package com.jb.petTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.dto.AuthRequestDTO;
import com.jb.petTracker.dto.LoginResponseDTO;
import com.jb.petTracker.model.User;
import com.jb.petTracker.service.AuthService;
import com.jb.petTracker.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private UserService userService;
	private AuthService authService;

	public AuthController(UserService service, AuthService authService) {
		super();
		this.userService = service;
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody User userInfo) {
		try {
			userService.save(userInfo);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
		boolean isAuthenticated = authService.isAuthenticated(authRequest);
		if (isAuthenticated) {
			String token = userService.login(authRequest.getUsername());
			return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
}