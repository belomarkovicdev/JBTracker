package com.jb.petTracker.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;

@Configuration
@IntegrationComponentScan
public class MqttConfig {
	@Value("${spring.mqtt.client.id}")
	private String clientId;
	@Value("${spring.mqtt.host}")
	private String mqttHost;
	@Value("${spring.mqtt.defaultTopic}")
	private String mqttDefaultTopic;

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(new String[] { mqttHost });
//	    options.setUserName("mojUser");
//	    options.setPassword("mojaLozinka".toCharArray());
		options.setCleanSession(true);
		factory.setConnectionOptions(options);
		return factory;
	}

	@Bean
	public MqttPahoMessageDrivenChannelAdapter mqttInbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttHost, clientId,
				mqttDefaultTopic);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}
}