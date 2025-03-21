package com.jb.petTracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jb.petTracker.dto.TraccarLocationDTO;
import com.jb.petTracker.model.DeviceTrackingSession;
import com.jb.petTracker.model.Location;
import com.jb.petTracker.repository.DeviceTrackingSessionRepository;
import com.jb.petTracker.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	private final DeviceTrackingSessionRepository locationMongoRepository;

	public LocationServiceImpl(DeviceTrackingSessionRepository locationMongoRepository) {
		super();
		this.locationMongoRepository = locationMongoRepository;
	}

	public DeviceTrackingSession getLocationHistory(String deviceId) {
		return locationMongoRepository.findByDeviceId(deviceId);
	}

	@Override
	public void saveLocation(TraccarLocationDTO traccarLocationDTO) {
		DeviceTrackingSession deviceLocations = locationMongoRepository.findByDeviceId(traccarLocationDTO.getId());
		if(deviceLocations != null) {
			deviceLocations.getLocations().add(new Location(traccarLocationDTO));
			locationMongoRepository.save(deviceLocations);
		}else {
			locationMongoRepository.save(new DeviceTrackingSession(traccarLocationDTO));
		}
	}


}
