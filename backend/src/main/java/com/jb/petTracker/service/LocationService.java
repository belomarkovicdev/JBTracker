package com.jb.petTracker.service;

import com.jb.petTracker.dto.TraccarLocationDTO;
import com.jb.petTracker.model.DeviceTrackingSession;
public interface LocationService {
	DeviceTrackingSession getLocationHistory(String deviceId);
	void saveLocation(TraccarLocationDTO traccarLocationDTO);
}
