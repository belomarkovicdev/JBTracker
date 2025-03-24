package com.jb.petTracker.dto;

import java.util.ArrayList;
import java.util.List;

import com.jb.petTracker.model.Device;
import com.jb.petTracker.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoggedInUserDTO {

	private String id = "";
	private String username = "";
	private String email = "";
	private List<Device> devices = new ArrayList<>();
	private String groupId;

	public LoggedInUserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.devices = user.getDevices();
		this.groupId = user.getGroupId();
	}
}
