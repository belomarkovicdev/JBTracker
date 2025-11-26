package com.jb.petTracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;

@Component
public class NettyService {
	private final int PORT = 5015;
	private final HuabaoHandler huabaoHandler;

	@Autowired
	public NettyService(HuabaoHandler huabaoHandler) {
		this.huabaoHandler = huabaoHandler;
	}

	@PostConstruct
	public void start() throws Exception {
		EventLoopGroup boss = new NioEventLoopGroup(1);
		EventLoopGroup worker = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) {
						ch.pipeline().addLast(new HuabaoFrameDecoder());
						ch.pipeline().addLast(new HuabaoMessageDecoder());
						ch.pipeline().addLast(huabaoHandler);
					}
				});

		bootstrap.bind("0.0.0.0",PORT).sync();
		System.out.println("📡 Huabao TCP server running on port " + PORT);
	}
}