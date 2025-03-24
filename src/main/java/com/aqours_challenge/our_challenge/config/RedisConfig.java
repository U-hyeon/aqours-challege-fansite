package com.aqours_challenge.our_challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession  // Redis에 HttpSession 저장
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String SPRING_REDIS_HOST;
    @Value("${spring.data.redis.port}")
    private int SPRING_REDIS_PORT;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(SPRING_REDIS_HOST);
        factory.setPort(SPRING_REDIS_PORT);
        return factory;
    }
}
