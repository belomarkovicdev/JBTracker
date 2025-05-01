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
import com.jb.petTracker.service.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupController {

	private final GroupService groupService;

	public GroupController(GroupService groupService) {
		super();
		this.groupService = groupService;
	}

	@PostMapping()
	public ResponseEntity<String> createGroup(@RequestBody Group newGroup,
			@RequestHeader("Authorization") String token) {
		groupService.create(newGroup, token);
		return new ResponseEntity<>("Grupa je uspesno kreirana", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Group> findById(@PathVariable String id) {
		return new ResponseEntity<>(groupService.findById(id), HttpStatus.OK);
	}
}