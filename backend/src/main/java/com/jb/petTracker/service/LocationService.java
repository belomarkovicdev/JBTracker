package com.jb.petTracker.service;

import org.springframework.stereotype.Service;

import com.jb.petTracker.model.Location;

@Service
public class LocationService {
	
    private Location latestLocation;

    public void saveLocation(Location location) {
        this.latestLocation = location;
    }

    public Location getLatestLocation() {
        return latestLocation;
    }
	
}
