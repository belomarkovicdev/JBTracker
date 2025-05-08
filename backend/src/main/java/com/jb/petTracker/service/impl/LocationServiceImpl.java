package com.jb.petTracker.service.impl;

import org.springframework.stereotype.Service;

import com.jb.petTracker.dto.DeviceLocationsDTO;
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
	public DeviceLocationsDTO getLocationHistory(String deviceId) {
		return new DeviceLocationsDTO(locationMongoRepository.findByDeviceId(deviceId));
	}

	@Override
	public DeviceLocations save(DeviceLocations deviceLocations) {
		return locationMongoRepository.save(deviceLocations);
	}

	@Override
	public boolean saveLocation(ReceiveLocationDTO receivedLocation) {
		DeviceLocations deviceLocations = locationMongoRepository.findByDeviceId(receivedLocation.getId());
		if (deviceLocations != null && deviceLocations.getLocations() != null) {
//				moze u getteru da se implementira ako ne postoje lokacije da vrati new ArrayList
			deviceLocations.getLocations().add(new Location(receivedLocation));
			save(deviceLocations);
			return true;
		}
		save(new DeviceLocations(receivedLocation));
		return true;
	}
}