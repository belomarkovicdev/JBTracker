package com.jb.petTracker.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.dto.GroupDTO;
import com.jb.petTracker.service.GroupService;
import com.jb.petTracker.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private UserService userService;
	private GroupService groupService;



	public UserController(UserService userService, GroupService groupService) {
		super();
		this.userService = userService;
		this.groupService = groupService;
	}

	@PutMapping("/{userId}/groups/add")
	public ResponseEntity<GroupDTO> addToGroup(@PathVariable String userId, @RequestParam String groupId) {
		userService.addToGroup(userId, new ObjectId(groupId));
		GroupDTO group = new GroupDTO(groupService.findById(groupId));
		return new ResponseEntity<>(group, HttpStatus.OK);
	}
}