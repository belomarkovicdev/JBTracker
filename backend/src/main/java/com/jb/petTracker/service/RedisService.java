package com.jb.petTracker.service;

import java.util.List;

import com.jb.petTracker.model.DeviceLocations;
import com.jb.petTracker.model.Group;
import com.jb.petTracker.model.LocationDetails;

public interface RedisService{
	void saveGroup(Group group);
	void saveDeviceLocations(String deviceId, DeviceLocations deviceLocations);
	Group getGroup(String groupId);
	List<DeviceLocations> getDeviceLocations(String deviceId);
	List<LocationDetails> getLastLocationsForDevice(String deviceId);

}
