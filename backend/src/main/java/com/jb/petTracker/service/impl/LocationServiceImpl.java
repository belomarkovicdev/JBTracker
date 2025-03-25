package com.jb.petTracker.service.impl;

import org.springframework.stereotype.Service;

import com.jb.petTracker.dto.ReceiveLocationDTO;
import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.Location;
import com.jb.petTracker.repository.DeviceLocationsRepository;
import com.jb.petTracker.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	private final DeviceLocationsRepository locationMongoRepository;

	public LocationServiceImpl(DeviceLocationsRepository locationMongoRepository) {
		super();
		this.locationMongoRepository = locationMongoRepository;
	}

	@Override
	public DeviceLocations getLocationHistory(String deviceId) {
		return locationMongoRepository.findByDeviceId(deviceId);
	}

	@Override
	public void saveLocation(ReceiveLocationDTO traccarLocationDTO) {
		DeviceLocations deviceLocations = locationMongoRepository.findByDeviceId(traccarLocationDTO.getId());
		if (deviceLocations != null) {
			deviceLocations.getLocations().add(new Location(traccarLocationDTO));
			locationMongoRepository.save(deviceLocations);
		} else {
			locationMongoRepository.save(new DeviceLocations(traccarLocationDTO));
		}
	}

}
