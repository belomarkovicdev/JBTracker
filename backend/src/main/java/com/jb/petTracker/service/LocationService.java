package com.jb.petTracker.service;

import com.jb.petTracker.dto.ReceiveLocationDTO;
import com.jb.petTracker.model.DeviceLocations;
public interface LocationService {
	DeviceLocations getLocationHistory(String deviceId);
	void saveLocation(ReceiveLocationDTO traccarLocationDTO);
}
