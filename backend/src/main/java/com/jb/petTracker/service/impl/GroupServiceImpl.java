package com.jb.petTracker.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jb.petTracker.model.Device;
import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.Group;
import com.jb.petTracker.model.User;
import com.jb.petTracker.repository.GroupRepository;
import com.jb.petTracker.service.GroupService;
import com.jb.petTracker.service.LocationService;
import com.jb.petTracker.service.UserService;

@Service
public class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;
	private final UserService userService;
	private final LocationService locationService;

	public GroupServiceImpl(GroupRepository groupRepository, UserService userService, LocationService locationService) {
		super();
		this.groupRepository = groupRepository;
		this.userService = userService;
		this.locationService = locationService;
	}

	@Override
	public Group create(Group group, String token) {
		User user = userService.extractUserFromToken(token.substring(7));
		List<DeviceLocations> deviceLocations = new ArrayList<>();
		for (Device device : user.getDevices()) {
			deviceLocations.add(locationService.save(new DeviceLocations(device.getDeviceId())));
		}
		group.setDeviceLocations(deviceLocations);
		Group createdGroup = this.groupRepository.save(group);
		user.setGroupId(createdGroup.getId());
		userService.update(user);
		return createdGroup;
	}

	@Override
	public Group findById(String id) {
		Optional<Group> group = groupRepository.findById(id);
		if (group.isPresent()) {
			return group.get();
		} else {
			return new Group();
		}
	}
}