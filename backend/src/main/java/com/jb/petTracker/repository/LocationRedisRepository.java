package com.jb.petTracker.repository;

public interface LocationRedisRepository<T> {
	public T findById(String id);
	public void save(T latestLocation, String locationId);
}
