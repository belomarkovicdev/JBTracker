package com.jb.petTracker.service;

import com.jb.petTracker.model.Group;

public interface GroupService {
	Group create(Group group);
	Group findById(String id);
}
