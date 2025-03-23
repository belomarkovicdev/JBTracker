package com.jb.petTracker.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jb.petTracker.model.Group;
import com.jb.petTracker.repository.GroupRepository;
import com.jb.petTracker.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	private final GroupRepository groupRepository;

	public GroupServiceImpl(GroupRepository groupRepository) {
		super();
		this.groupRepository = groupRepository;
	}

	@Override
	public Group create(Group group) {
		return this.groupRepository.save(group);
	}

	@Override
	public Group findById(String id) {
		Optional<Group> group = groupRepository.findById(id);
		if(group.isPresent()) {
			return group.get();
		}else {
			return null;
		}
	}
}
