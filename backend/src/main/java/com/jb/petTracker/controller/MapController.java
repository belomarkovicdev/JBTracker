package com.jb.petTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.model.Group;
import com.jb.petTracker.model.User;
import com.jb.petTracker.service.GroupService;
import com.jb.petTracker.service.UserService;

@RestController
@RequestMapping("/api/map")
public class MapController {

	private final UserService userService;
	private final GroupService groupService;

	public MapController(UserService userService, GroupService groupService) {
		super();
		this.userService = userService;
		this.groupService = groupService;
	}

	@GetMapping()
	public ResponseEntity<Group> getGroupLocations(@RequestHeader("Authorization") String token) {
		User user = userService.extractUserFromToken(token.substring(7));
		Group group = groupService.findById(user.getGroupId().toString());
		return new ResponseEntity<>(group, HttpStatus.OK);
	}
}
