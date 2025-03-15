package com.jb.petTracker.repository.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.jb.petTracker.model.LatestLocation;
import com.jb.petTracker.repository.LocationRedisRepository;

@Repository
public class LocationRedisRepositoryImpl implements LocationRedisRepository<LatestLocation>{

	private final RedisTemplate<String, LatestLocation> redisTemplate;
	private static final String REDIS_KEY_PREFIX = "device:";

	public LocationRedisRepositoryImpl(RedisTemplate<String, LatestLocation> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void save(LatestLocation latestLocation, String deviceId) {
		redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + deviceId, latestLocation, 20, TimeUnit.MINUTES);
	}

	public LatestLocation findById(String id) {
		return redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + id);
	}
}
