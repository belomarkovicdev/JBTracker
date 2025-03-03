package com.jb.petTracker.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.LatestLocation;
import com.jb.petTracker.model.Location;
import com.jb.petTracker.repository.LocationRepository;

@Service
public class LocationService {
	
	private final LocationRepository locationRepository;
	private final RedisTemplate<String, LatestLocation> redisTemplate;
    private static final String REDIS_KEY_PREFIX = "device:";
    
	public LocationService(LocationRepository locationRepository, RedisTemplate<String, LatestLocation> redisTemplate) {
		this.locationRepository = locationRepository;
		this.redisTemplate = redisTemplate;
	}
    
    public void saveLocation(Location location) {
        locationRepository.save(location);
        LatestLocation latestLocation = new LatestLocation(location);
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + location.getId(), latestLocation, 10, TimeUnit.MINUTES);
    }

    public LatestLocation getLatestLocation(Long id) {
        return redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + id);
    }
    public List<Location> getLocationHistory(Long id) {
        return locationRepository.findByIdOrderByTimestampDesc(id);
    }
}
