package com.jb.petTracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jb.petTracker.model.Group;

public interface GroupRepository extends MongoRepository<Group, String> {

}
