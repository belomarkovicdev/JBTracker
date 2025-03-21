package com.jb.petTracker.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jb.petTracker.model.Device;

public interface DeviceRepository extends MongoRepository<Device, String> {
	Optional<Device> findById(String id);
	<S extends Device> S save(S device);
}
