package com.jb.petTracker.service;

import com.jb.petTracker.model.AuthRequest;

public interface AuthService {
	boolean isAuthenticated(AuthRequest authRequest);
}
