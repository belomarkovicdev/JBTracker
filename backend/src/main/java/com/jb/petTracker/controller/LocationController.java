package com.jb.petTracker.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.dto.TraccarLocationDTO;
import com.jb.petTracker.model.Location;
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
	public void receiveLocation(@ModelAttribute TraccarLocationDTO traccarLocationDTO) {
		LocationDetails location = new LocationDetails(traccarLocationDTO);
		messagingTemplate.convertAndSend("/topic/location", location);
		locationService.saveLocation(location);
	}

	@GetMapping("/latest/{id}")
	public Location getLatestLocation(@PathVariable String id) {
		return locationService.getLatestLocation(id);
	}

	@GetMapping("/history/{id}")
//    ne radi zato sto se ne duplira @Id
	public List<LocationDetails> getLocationHistory(@PathVariable Long id) {
		return locationService.getLocationHistory(id);
	}
}
