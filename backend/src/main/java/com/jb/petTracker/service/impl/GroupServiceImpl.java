package com.jb.petTracker.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jb.petTracker.dto.GroupDTO;
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
//	Creates new group and adds all user devices to it
	public GroupDTO create(String groupName, String token) {
		Group group = new Group();
		group.setName(groupName);
		User user = userService.extractUserFromToken(token.substring(7));
		List<DeviceLocations> deviceLocations = getAllDeviceLocations(user);
		group.setDeviceLocations(deviceLocations);
		Group createdGroup = this.groupRepository.save(group);
		user.setGroupId(createdGroup.getId());
		userService.update(user);
		return new GroupDTO(createdGroup);
	}

	private List<DeviceLocations> getAllDeviceLocations(User user) {
		List<DeviceLocations> deviceLocations = new ArrayList<>();
		for (Device device : user.getDevices()) {
			DeviceLocations location = locationService.save(new DeviceLocations(device.getDeviceId()));
			deviceLocations.add(location);
		}
		return deviceLocations;
	}

	@Override
	public GroupDTO findById(String id) {
		Optional<Group> group = groupRepository.findById(id);
		if (group.isPresent()) {
			return new GroupDTO(group.get());
		} else {
			return new GroupDTO();
		}
	}
}