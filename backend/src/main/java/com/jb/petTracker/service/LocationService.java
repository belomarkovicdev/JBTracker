package com.jb.petTracker.service;

import java.util.List;

import com.jb.petTracker.model.LatestLocation;
import com.jb.petTracker.model.LocationDetails;

public interface LocationService {
	void saveLocation(LocationDetails location);
	LatestLocation getLatestLocation(String id);
	List<LocationDetails> getLocationHistory(Long id);
}
