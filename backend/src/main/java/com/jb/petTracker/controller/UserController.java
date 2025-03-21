package com.jb.petTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.model.AuthRequest;
import com.jb.petTracker.model.User;
import com.jb.petTracker.service.AuthService;
import com.jb.petTracker.service.JwtService;
import com.jb.petTracker.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	private UserService userInfoService;
	private JwtService jwtService;
	private AuthService authService;

	public UserController(UserService service, JwtService jwtService, AuthService authService) {
		super();
		this.userInfoService = service;
		this.jwtService = jwtService;
		this.authService = authService;
	}

	@PostMapping("/addNewUser")
	public ResponseEntity<String> addNewUser(@RequestBody User userInfo) {
		userInfoService.addUser(userInfo);
		return new ResponseEntity<>("User added successfully",HttpStatus.OK);
	}

	@GetMapping("/user/userProfile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userProfile() {
		return "Welcome to User Profile";
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminProfile() {
		return "Welcome to Admin Profile";
	}

	@PostMapping("/generateToken")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		if (authService.isAuthenticated(authRequest)) {
			return new ResponseEntity<>(jwtService.generateToken(authRequest.getUsername()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
		}
	}
}