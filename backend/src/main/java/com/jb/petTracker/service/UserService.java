package com.jb.petTracker.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;

import com.jb.petTracker.dto.DeviceDTO;
import com.jb.petTracker.model.Device;
import com.jb.petTracker.model.User;

public interface UserService{
	UserDetails loadUserByUsername(String username);
	Optional<User> findByUsername(String username);
	boolean save(User user);
	User update(User user);
	boolean addDevice(String token, Device device);
	List<DeviceDTO> getDevices(String username);
	String generateToken(String username);
	boolean addToGroup(String username, ObjectId groupId);
	User extractUserFromToken(String token);
}
