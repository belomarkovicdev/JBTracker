package com.jb.petTracker.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.AuthUser;
import com.jb.petTracker.model.Device;
import com.jb.petTracker.model.User;
import com.jb.petTracker.repository.UserRepository;
import com.jb.petTracker.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	public UserServiceImpl() {
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByUsername(username);
		return user.map(AuthUser::new).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

	public void addUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
	}

	@Override
	public void addDevice(String username, Device device) {
		Optional<User> user = findByUsername(username);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		user.get().getDevices().add(device);
		repository.save(user.get());
	}

	@Override
	public List<Device> getDevices(String username) {
		Optional<User> user = findByUsername(username);
		return user.get().getDevices();
	}
}
