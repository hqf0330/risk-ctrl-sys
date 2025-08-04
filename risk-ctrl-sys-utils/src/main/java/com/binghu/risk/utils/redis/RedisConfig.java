package com.binghu.risk.utils.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Resource
    private RedisPoolProperties redisPoolProperties;

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();

        // k v序列化配置
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 连接驱动配置
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {

        // redis集群配置
        Map<String, Object> map = new HashMap<>();
        map.put("spring.redis.cluster.nodes", redisProperties.getNodes());

        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration(
                new MapPropertySource("RedisClusterConfiguration", map));

        // redis客户端配置
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisPoolProperties.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisPoolProperties.getMinIdle());
        genericObjectPoolConfig.setMaxTotal(redisPoolProperties.getMaxActive());
        genericObjectPoolConfig.setMaxWait(Duration.ofDays(redisPoolProperties.getMaxWait()));

        LettucePoolingClientConfiguration redisClientConfig = LettucePoolingClientConfiguration
                .builder()
                .poolConfig(genericObjectPoolConfig)
                .build();

        return new LettuceConnectionFactory(redisConfig, redisClientConfig);
    }
}
