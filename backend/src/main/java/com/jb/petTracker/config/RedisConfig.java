package com.jb.petTracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.jb.petTracker.model.LatestLocation;

@Configuration
public class RedisConfig {
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, LatestLocation> redisTemplate() {
		RedisTemplate<String, LatestLocation> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
        Jackson2JsonRedisSerializer<LatestLocation> serializer = new Jackson2JsonRedisSerializer<>(LatestLocation.class);
        template.setDefaultSerializer(serializer);
		return template;
	}
}
