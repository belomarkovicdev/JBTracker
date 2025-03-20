package com.jb.petTracker.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.jb.petTracker.model.User;

public interface UserService{
	UserDetails loadUserByUsername(String username);
	String addUser(User user);

}
