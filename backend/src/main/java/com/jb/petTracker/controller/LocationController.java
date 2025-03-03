package com.jb.petTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.model.Location;
import com.jb.petTracker.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	private final LocationService locationService;
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
    // Endpoint za primanje GPS podataka od Traccar servera
    @PostMapping()
    public void receiveLocation(@ModelAttribute Location location) {
    	System.out.println(location);
        locationService.saveLocation(location);
    }

    // Endpoint za dobijanje poslednjih GPS podataka
    @GetMapping()
    public Location getLatestLocation() {
        return locationService.getLatestLocation();
    }
}
