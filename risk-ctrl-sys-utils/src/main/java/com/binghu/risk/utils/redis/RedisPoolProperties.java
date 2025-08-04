package com.binghu.risk.utils.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
public class RedisPoolProperties {

    private Integer maxIdle;

    private Integer minIdle;

    private Integer maxActive;

    private Integer maxWait;
}
