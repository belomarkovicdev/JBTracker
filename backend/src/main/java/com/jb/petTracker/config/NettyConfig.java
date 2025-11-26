package com.jb.petTracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.jb.petTracker.service.impl.HuabaoHandler;

@Configuration
public class NettyConfig {

    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;

    @Bean
    public HuabaoHandler huabaoHandler() {
        return new HuabaoHandler(simpleMessagingTemplate);
    }
}

