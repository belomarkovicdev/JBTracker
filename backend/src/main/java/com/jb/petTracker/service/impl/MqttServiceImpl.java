package com.jb.petTracker.service.impl;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.jb.petTracker.service.MqttService;
@Service
public class MqttServiceImpl implements MqttService {
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public void handleMessage(Message<?> message) {
		String payload = (String) message.getPayload();
		System.out.println("Primljena MQTT poruka: " + payload);

		// Ovde možeš implementirati logiku koju želiš da izvršiš sa porukom
		// Na primer, možeš da proslediš poruku u neki drugi servis ili bazu podataka
	}
}
