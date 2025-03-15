package com.jb.petTracker.model;


public class LatestLocation extends Location {
	private float speed;

	public LatestLocation() {
		super();
	}

	public LatestLocation(float speed) {
		this.speed = speed;
	}

	public LatestLocation(LocationDetails location) {
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}