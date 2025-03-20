package com.jb.petTracker.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
	
	String generateToken(String username);
	Date extractExpiration(String token);
	String extractUsername(String token);
	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);
	Boolean validateToken(String token, UserDetails userDetails);
}
