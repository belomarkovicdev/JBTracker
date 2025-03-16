package com.jb.petTracker.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.LatestLocation;
import com.jb.petTracker.model.LocationDetails;
import com.jb.petTracker.repository.LocationMongoRepository;
import com.jb.petTracker.repository.LocationRedisRepository;

@Service
public class LocationService {

	private final LocationMongoRepository locationMongoRepository;
	private final LocationRedisRepository<LatestLocation> locationRedisRepository;

	public LocationService(LocationMongoRepository locationMongoRepository,
			LocationRedisRepository<LatestLocation> locationRedisRepository) {
		super();
		this.locationMongoRepository = locationMongoRepository;
		this.locationRedisRepository = locationRedisRepository;
	}

	public void saveLocation(LocationDetails location) {
		locationMongoRepository.save(location);
		LatestLocation latestLocation = new LatestLocation(location);
		locationRedisRepository.save(latestLocation, location.getId());
	}

	public LatestLocation getLatestLocation(String id) {
		return locationRedisRepository.findById(id);
	}

	public List<LocationDetails> getLocationHistory(Long id) {
		return locationMongoRepository.findByIdOrderByTimestampDesc(id);
	}
}
