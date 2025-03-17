package com.jb.petTracker.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

	// Ovaj metod će biti pozvan kada nova poruka stigne na kanal "mqttInputChannel"
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public void handleMessage(Message<?> message) {
		String payload = (String) message.getPayload();
		System.out.println("Primljena MQTT poruka: " + payload);

		// Ovde možeš implementirati logiku koju želiš da izvršiš sa porukom
		// Na primer, možeš da proslediš poruku u neki drugi servis ili bazu podataka
	}
}
