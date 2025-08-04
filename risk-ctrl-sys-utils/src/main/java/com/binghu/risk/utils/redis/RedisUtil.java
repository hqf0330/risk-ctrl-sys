package com.binghu.risk.utils.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    public void stringSet(String key, Object value) {
       redisTemplate.opsForValue().set(key, value);
    }

    public Object stringGet(String key) {
       return redisTemplate.opsForValue().get(key);
    }

    public void hashSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Object hashGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }
}
