package redis.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import redis.example.model.Session;

/**
Created by: aganapathy@ebsco.com
Date: 4/1/18
*/
@Configuration
@ComponentScan("redis.example")
public class RedisConfig {
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(5);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);

		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
		connectionFactory.setUsePool(true);
		connectionFactory.setHostName(""); // The public endpoint of the redis cache
		connectionFactory.setPort(6379);
		return connectionFactory;
	}
	@Bean
	public RedisTemplate<String, Session> redisTemplate() {
		RedisTemplate<String, Session> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}
	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory());
		stringRedisTemplate.setEnableTransactionSupport(true);
		return stringRedisTemplate;
	}
}