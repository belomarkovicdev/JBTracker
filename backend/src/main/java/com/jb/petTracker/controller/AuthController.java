package com.jb.petTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.dto.AuthRequestDTO;
import com.jb.petTracker.dto.LoginResponseDTO;
import com.jb.petTracker.dto.RegisteredUserDTO;
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
	public ResponseEntity<RegisteredUserDTO> register(@RequestBody User userInfo) {
		RegisteredUserDTO savedUser = new RegisteredUserDTO(userService.addUser(userInfo));
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
		if (authService.isAuthenticated(authRequest)) {
			return new ResponseEntity<>(userService.login(authRequest.getUsername()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

//	@GetMapping("/user/userProfile")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public String userProfile() {
//		return "Welcome to User Profile";
//	}
//
//	@GetMapping("/admin/adminProfile")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public String adminProfile() {
//		return "Welcome to Admin Profile";
//	}
}