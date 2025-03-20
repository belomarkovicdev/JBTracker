package com.jb.petTracker.repository; 

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jb.petTracker.model.DeviceLocations;

@Repository
public interface LocationMongoRepository extends MongoRepository<DeviceLocations, Long> {
    DeviceLocations findByDeviceId(String id);
    
}