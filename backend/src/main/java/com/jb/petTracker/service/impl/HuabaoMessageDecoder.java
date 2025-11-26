package com.jb.petTracker.service.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import com.jb.petTracker.model.HuabaoPosition;

public class HuabaoMessageDecoder extends MessageToMessageDecoder<byte[]> {

	@Override
	protected void decode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) {
		if (msg.length < 25)
			return;

		HuabaoPosition pos = new HuabaoPosition();

		// 0: msgType
		// 1-2: attributes
		// 3-10: IMEI (BCD)
		byte[] imeiBytes = new byte[8];
		System.arraycopy(msg, 3, imeiBytes, 0, 8);
		pos.imei = bcdToString(imeiBytes);

		// GPS payload (najčešće kod 0x0200)
		int latRaw = readInt(msg, 11);
		int lonRaw = readInt(msg, 15);
		int speed = ((msg[19] & 0xFF) << 8) | (msg[20] & 0xFF);
		int course = ((msg[21] & 0xFF) << 8) | (msg[22] & 0xFF);

		pos.lat = latRaw / 1800000.0;
		pos.lon = lonRaw / 1800000.0;
		pos.speed = speed;
		pos.course = course;

		out.add(pos);
	}

	private int readInt(byte[] data, int offset) {
		return (data[offset] & 0xFF) << 24 | (data[offset + 1] & 0xFF) << 16 | (data[offset + 2] & 0xFF) << 8
				| (data[offset + 3] & 0xFF);
	}

	private String bcdToString(byte[] arr) {
		StringBuilder sb = new StringBuilder();
		for (byte b : arr)
			sb.append(String.format("%02X", b));
		return sb.toString();
	}
}