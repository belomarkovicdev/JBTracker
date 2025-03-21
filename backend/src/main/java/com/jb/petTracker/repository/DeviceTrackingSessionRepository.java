package com.jb.petTracker.repository; 


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jb.petTracker.model.DeviceTrackingSession;

@Repository
public interface DeviceTrackingSessionRepository extends MongoRepository<DeviceTrackingSession, String> {
    DeviceTrackingSession findByDeviceId(String id);
}