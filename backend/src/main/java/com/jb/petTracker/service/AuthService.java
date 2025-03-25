package com.jb.petTracker.service;

import com.jb.petTracker.dto.AuthRequestDTO;

public interface AuthService {
	boolean isAuthenticated(AuthRequestDTO authRequest);
}
