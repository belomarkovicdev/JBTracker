package com.jb.petTracker.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.User;
import com.jb.petTracker.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtServiceImpl implements JwtService {
	public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";
	
	public JwtServiceImpl() {
		super();
	}

	@Override
	public String generateToken(User u) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", u.getUsername());
		claims.put("userId", u.getId());
		claims.put("roles", u.getRoles());
		return createToken(claims, u);
	}

	private String createToken(Map<String, Object> claims, User user) {
		String token = Jwts.builder().claims(claims).subject(user.getUsername()).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).signWith(getSignKey()).compact();
		return token;
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
		try {
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);			
		}catch(NullPointerException e) {
			return null;
		}
	}

	private Claims extractAllClaims(String token) {
		try {
			return Jwts.parser().verifyWith((SecretKey) getSignKey()).build().parseSignedClaims(token).getPayload();			
		}catch (ExpiredJwtException e){
			return null;
		}
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
