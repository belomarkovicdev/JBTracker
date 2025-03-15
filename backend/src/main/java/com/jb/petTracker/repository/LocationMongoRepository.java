package com.jb.petTracker.repository; 

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jb.petTracker.model.LocationDetails;

@Repository
public interface LocationMongoRepository extends MongoRepository<LocationDetails, Long> {
    List<LocationDetails> findByIdOrderByTimestampDesc(Long id);
}