package com.jb.petTracker.service;

import com.jb.petTracker.dto.TraccarLocationDTO;
import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.LatestLocation;
public interface LocationService {
	LatestLocation getLatestLocation(String id);
	DeviceLocations getLocationHistory(String deviceId);
	void saveLocation(TraccarLocationDTO traccarLocationDTO);
}
