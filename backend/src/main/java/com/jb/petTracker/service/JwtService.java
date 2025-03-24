package com.jb.petTracker.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import com.jb.petTracker.model.User;

import io.jsonwebtoken.Claims;

public interface JwtService {
	
	String generateToken(User user);
	Date extractExpiration(String token);
	String extractUsername(String token);
	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);
	Boolean validateToken(String token, UserDetails userDetails);
}
