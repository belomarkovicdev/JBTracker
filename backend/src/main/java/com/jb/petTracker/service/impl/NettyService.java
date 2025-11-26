package com.jb.petTracker.service.impl;

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
						// Kreiramo novu instancu handlera za svaku konekciju
						ch.pipeline().addLast(new HuabaoHandler());
					}
				});

		bootstrap.bind("0.0.0.0", PORT).sync();
		System.out.println("📡 Huabao TCP server running on port " + PORT);
	}
}
