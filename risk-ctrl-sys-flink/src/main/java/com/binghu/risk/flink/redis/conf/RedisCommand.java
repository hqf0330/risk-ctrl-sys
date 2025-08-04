package com.binghu.risk.flink.redis.conf;

import lombok.Getter;

/**
 * redis命令的枚举类
 */
@Getter
public enum RedisCommand {
    GET(RedisDataType.STRING);

    private RedisDataType redisDataType;

    RedisCommand(RedisDataType redisDataType) {
        this.redisDataType = redisDataType;
    }

}
