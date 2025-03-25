package com.jb.petTracker.repository; 


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jb.petTracker.model.DeviceLocations;

@Repository
public interface DeviceLocationsRepository extends MongoRepository<DeviceLocations, String> {
    DeviceLocations findByDeviceId(String id);
}