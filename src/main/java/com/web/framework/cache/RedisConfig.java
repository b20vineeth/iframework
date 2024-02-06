package com.web.framework.cache;

import java.io.Serializable;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
 
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
public class RedisConfig {

	@Autowired
	private CacheManager cacheManager;

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Bean
	public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

//	@Bean
//	public CacheManager cacheManager(RedisConnectionFactory factory, ObjectMapper objectMapper) {
//	//public CacheManager cacheManager(RedisConnectionFactory factory) {
//	/*
//	   	RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//	
//		RedisCacheConfiguration redisCacheConfiguration = config
//				.serializeKeysWith(
//						RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//				.serializeValuesWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(new GenericJackson2JsonRedisSerializer()))
//				.entryTtl(Duration.ofMinutes(10));
//		RedisCacheManager redisCacheManager = RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration)
//				.build();
//		return redisCacheManager;
//	 */
//		
//		 RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//	                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//	                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new CustomJsonRedisSerializer<>(objectMapper)))
//	                .entryTtl(Duration.ofMinutes(10)); // Set expiration time to 10 minutes
//
//	        return RedisCacheManager.builder(factory)
//	                .cacheDefaults(config)
//	                .build();
//	}
	
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory,ObjectMapper objectMapper) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                		.fromSerializer(new CustomJsonRedisSerializer(objectMapper)))
                .entryTtl(Duration.ofMinutes(10));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }

 

}
