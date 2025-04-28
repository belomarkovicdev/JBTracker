package com.jb.petTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ReceiveLocationDTO {
	private String id; //deviceId
	private double lat;
	private double lon;
	private long timestamp;
	private double accuracy;
	private String speed;
}
