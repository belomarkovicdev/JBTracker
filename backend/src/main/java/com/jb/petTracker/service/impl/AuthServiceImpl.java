package com.jb.petTracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.AuthRequest;
import com.jb.petTracker.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public boolean isAuthenticated(AuthRequest authRequest) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        return authentication.isAuthenticated();
	    } catch (AuthenticationException e) {
	        System.out.println("Autentifikacija nije uspela: " + e.getMessage());
	        return false;
	    }
	}

}
