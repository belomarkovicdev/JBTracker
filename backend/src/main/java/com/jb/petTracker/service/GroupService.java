package com.jb.petTracker.service;

import com.jb.petTracker.dto.GroupDTO;

public interface GroupService {
	GroupDTO create(String groupName, String token);
	GroupDTO findById(String id);
}
