package com.jb.petTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.User;
import com.jb.petTracker.model.AuthUser;
import com.jb.petTracker.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	public UserService() {
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByUsername(username);
		return user.map(AuthUser::new).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

	public String addUser(User user) {
		// Encode password before saving the user
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
		return "User Added Successfully";
	}
}
