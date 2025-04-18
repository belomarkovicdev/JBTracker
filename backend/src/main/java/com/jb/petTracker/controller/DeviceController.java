package com.jb.petTracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.dto.SaveDeviceDTO;
import com.jb.petTracker.model.Device;
import com.jb.petTracker.service.UserService;

@RestController
@RequestMapping("/api/device")
public class DeviceController {
	private final UserService userService;
	
	public DeviceController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public void addNewDevice(@RequestBody SaveDeviceDTO deviceDTO, @RequestHeader("Authorization") String token) {
		userService.addDevice(token, new Device(deviceDTO));
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<List<Device>> getDevices(@PathVariable String username){
		return new ResponseEntity<>(userService.getDevices(username),HttpStatus.OK);
	}
}
