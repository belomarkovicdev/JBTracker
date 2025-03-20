package com.jb.petTracker.service.impl;

import org.springframework.stereotype.Service;

import com.jb.petTracker.dto.TraccarLocationDTO;
import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.LatestLocation;
import com.jb.petTracker.model.Location;
import com.jb.petTracker.repository.LocationMongoRepository;
import com.jb.petTracker.repository.LocationRedisRepository;
import com.jb.petTracker.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	private final LocationMongoRepository locationMongoRepository;
	private final LocationRedisRepository<LatestLocation> locationRedisRepository;

	public LocationServiceImpl(LocationMongoRepository locationMongoRepository,
			LocationRedisRepository<LatestLocation> locationRedisRepository) {
		super();
		this.locationMongoRepository = locationMongoRepository;
		this.locationRedisRepository = locationRedisRepository;
	}

	public LatestLocation getLatestLocation(String id) {
		return locationRedisRepository.findById(id);
	}

	public DeviceLocations getLocationHistory(String deviceId) {
		return locationMongoRepository.findByDeviceId(deviceId);
	}

	@Override
	public void saveLocation(TraccarLocationDTO traccarLocationDTO) {
		DeviceLocations deviceLocations = locationMongoRepository.findByDeviceId(traccarLocationDTO.getId());
		if(deviceLocations != null) {
			deviceLocations.getLocations().add(new Location(traccarLocationDTO));
			locationMongoRepository.save(deviceLocations);
		}else {
			locationMongoRepository.save(new DeviceLocations(traccarLocationDTO));
		}
	}


}
