package com.jb.petTracker.service;

import java.util.List;

import com.jb.petTracker.dto.TraccarLocationDTO;
import com.jb.petTracker.model.DeviceTrackingSession;
public interface LocationService {
	List<DeviceTrackingSession> getLocationHistory(String deviceId);
	void saveLocation(TraccarLocationDTO traccarLocationDTO);
}
