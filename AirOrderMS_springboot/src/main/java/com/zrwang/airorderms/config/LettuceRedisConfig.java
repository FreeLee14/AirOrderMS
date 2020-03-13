package com.zrwang.airorderms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;

/**
 * 自定义redistemplate设定使用json进行序列化
 */
@Configuration
public class LettuceRedisConfig {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {

        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //受用json序列化器继续value的序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setConnectionFactory(connectionFactory);

        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        //初始化一个writer，属于创建rediscachemanager的一个依赖对象
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        //Employee.class针对employee设定单独的一个cachemanager
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Serializable.class);
        RedisSerializationContext.SerializationPair<Serializable> pair = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer);
        //创建RedisCacheConfiguration,属于创建rediscachemanager的一个依赖对象,使用defaultCacheConfig()创建的创建RedisCacheConfiguration对象默认开启了一些配置
        /*      如下为默认设置的配置
                Duration.ZERO, true, true, CacheKeyPrefix.simple(),
				SerializationPair.fromSerializer(RedisSerializer.string()),
				SerializationPair.fromSerializer(RedisSerializer.java(classLoader)), conversionService
        * */
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        //设定缓存默认超时期时间为30s这里用到了jdk1.8的duration
        redisCacheConfiguration.entryTtl(Duration.ofSeconds(10));
        redisCacheConfiguration.usePrefix();
        //创建rediscachemanager
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);

        return redisCacheManager;

    }

}
