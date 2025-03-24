package com.jb.petTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.model.Group;
import com.jb.petTracker.model.User;
import com.jb.petTracker.service.GroupService;
import com.jb.petTracker.service.UserService;

@RestController
@RequestMapping("/api/group")
public class GroupController {

	private final GroupService groupService;
	private final UserService userService;

	public GroupController(GroupService groupService, UserService userService) {
		super();
		this.groupService = groupService;
		this.userService = userService;
	}

	@PostMapping()
	public ResponseEntity<String> createGroup(@RequestBody Group group) {
		groupService.create(group);
		return new ResponseEntity<>("Grupa je uspesno kreirana", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Group> findById(@PathVariable String id) {
		return new ResponseEntity<>(groupService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/{groupId}/overview")
	public ResponseEntity<Group> getGroup(@RequestHeader("Authorization") String token, @PathVariable String groupId) {
		String jwt = token.substring(7);
		User user = userService.extractUserFromToken(jwt);
		try {
			boolean isMember = user.getGroupId().equals(groupId);
			if (isMember) {
				Group group = groupService.findById(groupId);
				return new ResponseEntity<>(group, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
			}
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}