package com.jb.petTracker.service;
import com.jb.petTracker.model.Group;

public interface GroupService {
	Group create(Group group, String token);
	Group findById(String id);
}
