package com.jb.petTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jb.petTracker.dto.ReceiveLocationDTO;
import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {

	private final LocationService locationService;
	private final SimpMessagingTemplate messagingTemplate;

	public LocationController(LocationService locationService, SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
		this.locationService = locationService;
	}

	@PostMapping("/traccar")
	public ReceiveLocationDTO receiveLocation(@ModelAttribute ReceiveLocationDTO receiveLocationDTO) {
		boolean saved = locationService.saveLocation(receiveLocationDTO);
		if (saved) {
			System.out.println(receiveLocationDTO);
			// Ako želite da šaljete potvrdu, možete vratiti DTO objekat
			messagingTemplate.convertAndSend("/topic/locations", receiveLocationDTO);
			return receiveLocationDTO; // Ovo će biti automatski konvertovano u JSON
		} else {
			// Ako želite da šaljete grešku, možete vratiti odgovarajući DTO ili string
			// poruku
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doslo je do greske");
		}
	}

	@PostMapping()
	@SendTo("/topic/location")
	public ReceiveLocationDTO receiveBody(@RequestBody ReceiveLocationDTO receiveLocationDTO) {
		boolean saved = locationService.saveLocation(receiveLocationDTO);
		if (saved) {
			messagingTemplate.convertAndSend("/topic/locations", receiveLocationDTO);
			System.out.println(receiveLocationDTO);
			// Ako želite da šaljete potvrdu, možete vratiti DTO objekat
			return receiveLocationDTO; // Ovo će biti automatski konvertovano u JSON
		} else {
			// Ako želite da šaljete grešku, možete vratiti odgovarajući DTO ili string
			// poruku
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doslo je do greske");
		}
	}

	@GetMapping("/device/{id}")
	public ResponseEntity<DeviceLocations> getLocationHistory(@PathVariable String id) {
		return new ResponseEntity<>(locationService.getLocationHistory(id), HttpStatus.OK);
	}
}
