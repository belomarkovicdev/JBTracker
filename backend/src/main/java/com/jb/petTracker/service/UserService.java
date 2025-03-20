package com.jb.petTracker.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.jb.petTracker.model.User;

public interface UserService{
	UserDetails loadUserByUsername(String username);
	Optional<User> findByUsername(String username);
	String addUser(User user);

}
