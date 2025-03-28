package com.jb.petTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.jb.petTracker.model.Device;
import com.jb.petTracker.model.User;

public interface UserService{
	UserDetails loadUserByUsername(String username);
	Optional<User> findByUsername(String username);
	User addUser(User user);
	void addDevice(String username, Device device);
	List<Device> getDevices(String username);
	String login(String username);
	boolean addToGroup(String username, String groupId);
	User extractUserFromToken(String token);
}
