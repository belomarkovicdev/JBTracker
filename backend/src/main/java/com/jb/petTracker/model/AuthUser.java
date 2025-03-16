package com.jb.petTracker.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<GrantedAuthority> authorities;

	public AuthUser(User user) {
		this.user = user;
		this.authorities = List.of(user.getRoles().split(",")).stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	public AuthUser() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public User getUser() {
		return user;
	}
}
