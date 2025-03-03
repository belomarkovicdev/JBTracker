package com.jb.petTracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jb.petTracker.model.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, Long> {
    List<Location> findByIdOrderByTimestampDesc(Long id);
}