package com.jb.petTracker.service;

import com.jb.petTracker.dto.DeviceLocationsDTO;
import com.jb.petTracker.dto.ReceiveLocationDTO;
import com.jb.petTracker.model.DeviceLocations;
public interface LocationService {
	DeviceLocationsDTO getLocationHistory(String deviceId);
	DeviceLocations save(DeviceLocations deviceLocations);
	boolean saveLocation(ReceiveLocationDTO traccarLocationDTO);
}
