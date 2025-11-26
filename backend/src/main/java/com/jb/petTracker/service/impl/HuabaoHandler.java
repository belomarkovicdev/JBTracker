package com.jb.petTracker.service.impl;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.jb.petTracker.dto.ReceiveLocationDTO;
import com.jb.petTracker.model.HuabaoPosition;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HuabaoHandler extends SimpleChannelInboundHandler<HuabaoPosition> {

	private SimpMessagingTemplate simpleMessagingTemplate;

	public HuabaoHandler() {
		super();
	}

	public HuabaoHandler(SimpMessagingTemplate simpleMessagingTemplate) {
		super();
		this.simpleMessagingTemplate = simpleMessagingTemplate;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HuabaoPosition pos) {
		ReceiveLocationDTO receiveLocationDTO = new ReceiveLocationDTO();
		receiveLocationDTO.setId(pos.imei);
		receiveLocationDTO.setLat(pos.lat);
		receiveLocationDTO.setLon(pos.lon);
		receiveLocationDTO.setSpeed(Integer.toString(pos.speed));
		receiveLocationDTO.setAccuracy(0);
		receiveLocationDTO.setTimestamp(System.currentTimeMillis());
		simpleMessagingTemplate.convertAndSend("/topic/locations", receiveLocationDTO);
		System.out.println("📍 DEVICE " + pos.imei + " LAT: " + pos.lat + " LON: " + pos.lon + " SPEED: " + pos.speed
				+ " COURSE: " + pos.course);

		// 👉 OVDE UPISUJEŠ U MONGO DB
		// mongoRepository.save(new DeviceLocations(pos))
	}
}
