package com.jb.petTracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.Group;
import com.jb.petTracker.model.LocationDetails;
import com.jb.petTracker.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	// Metod za čuvanje grupnih podataka
	public void saveGroup(Group group) {
		redisTemplate.opsForValue().set("group:" + group.getId(), group);
	}

	// Metod za čuvanje podataka o uređaju
	public void saveDeviceLocations(String deviceId, DeviceLocations deviceLocations) {
		redisTemplate.opsForList().leftPush("device:" + deviceId, deviceLocations);
	}

	// Metod za preuzimanje podataka o grupi
	public Group getGroup(String groupId) {
		return (Group) redisTemplate.opsForValue().get("group:" + groupId);
	}

	// Metod za preuzimanje lokacija za uređaj
	public List<DeviceLocations> getDeviceLocations(String deviceId) {
//		return (List<DeviceLocations>) redisTemplate.opsForList().range("device:" + deviceId, 0, -1);
		return new ArrayList<>();
	}

	// Metod za čitanje poslednjih lokacija za uređaj
	public List<LocationDetails> getLastLocationsForDevice(String deviceId) {
		List<DeviceLocations> deviceLocationsList = getDeviceLocations(deviceId);
		if (deviceLocationsList != null && !deviceLocationsList.isEmpty()) {
//			return deviceLocationsList.get(deviceLocationsList.size() - 1).getLocations();
			return new ArrayList<>();
		}
		return null;
	}
}
