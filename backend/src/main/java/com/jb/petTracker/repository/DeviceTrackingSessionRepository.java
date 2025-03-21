package com.jb.petTracker.repository; 

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jb.petTracker.model.DeviceTrackingSession;

@Repository
public interface DeviceTrackingSessionRepository extends MongoRepository<DeviceTrackingSession, String> {
    DeviceTrackingSession findByDeviceId(String id);
    @Query("{'deviceId': ?0}")
    List<DeviceTrackingSession> findAllByDeviceId(String deviceId);
}