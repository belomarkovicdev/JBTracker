package com.jb.petTracker.controller;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.petTracker.dto.TraccarLocationDTO;
import com.jb.petTracker.model.LatestLocation;
import com.jb.petTracker.model.Location;
import com.jb.petTracker.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	
	private final LocationService locationService;
	
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}

    @PostMapping()
    public void receiveLocation(@ModelAttribute TraccarLocationDTO traccarLocationDTO) {
    	Location location = new Location(
    			traccarLocationDTO.getId(),
    			new Point(traccarLocationDTO.getLat(),traccarLocationDTO.getLon()),
    			traccarLocationDTO.getAccuracy(),
    			traccarLocationDTO.getBatt()
    			);
        locationService.saveLocation(location);
    }
    
    @GetMapping("/latest/{id}")
    public LatestLocation getLatestLocation(@PathVariable Long id) {
        return locationService.getLatestLocation(id);
    }

    @GetMapping("/history/{id}")
    public List<Location> getLocationHistory(@PathVariable Long id) {
        return locationService.getLocationHistory(id);
    }
}
