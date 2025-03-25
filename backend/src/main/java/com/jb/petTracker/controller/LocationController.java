package com.jb.petTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.dto.ReceiveLocationDTO;
import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.LocationDetails;
import com.jb.petTracker.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {

	private final LocationService locationService;
	private final SimpMessagingTemplate messagingTemplate;

	public LocationController(LocationService locationService, SimpMessagingTemplate messagingTemplate) {
		this.locationService = locationService;
		this.messagingTemplate = messagingTemplate;
	}

	@PostMapping()
	@SendTo("/topic/location")
	public void receiveLocation(@ModelAttribute ReceiveLocationDTO receiveLocationDTO) {
		LocationDetails location = new LocationDetails(receiveLocationDTO);
		messagingTemplate.convertAndSend("/topic/location", location);
		locationService.saveLocation(receiveLocationDTO);
	}

	@GetMapping("/device/{id}")
	public ResponseEntity<DeviceLocations> getLocationHistory(@PathVariable String id) {
		return new ResponseEntity<>(locationService.getLocationHistory(id), HttpStatus.OK);
	}
}
