package com.jb.petTracker.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.User;
import com.jb.petTracker.service.JwtService;
import com.jb.petTracker.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtServiceImpl implements JwtService {
	public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";
	private final UserService userService;
	
	
	public JwtServiceImpl(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		Optional<User> u = userService.findByUsername(username);
		claims.put("username", u.get().getUsername());
		claims.put("userId", u.get().getId());
		claims.put("roles", u.get().getRoles());
		claims.put("email", u.get().getEmail());
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder().claims(claims).subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).signWith(getSignKey()).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	@Override
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith((SecretKey) getSignKey()).build().parseSignedClaims(token).getPayload();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	@Override
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
