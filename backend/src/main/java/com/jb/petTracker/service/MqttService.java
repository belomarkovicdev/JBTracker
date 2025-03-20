package com.jb.petTracker.service;

import org.springframework.messaging.Message;

public interface MqttService {
	void handleMessage(Message<?> message);
}
