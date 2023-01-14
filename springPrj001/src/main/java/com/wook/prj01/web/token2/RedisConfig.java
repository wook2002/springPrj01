package com.wook.prj01.web.token2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
	
//	https://pearlluck.tistory.com/727
	
	@Value("${spring.redis.host}")
	private String redisHost;
	
	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		System.out.println("redisHost : " + redisHost);
		System.out.println("redisPort : " + redisPort);
		return new LettuceConnectionFactory(redisHost, redisPort);
	}

}
