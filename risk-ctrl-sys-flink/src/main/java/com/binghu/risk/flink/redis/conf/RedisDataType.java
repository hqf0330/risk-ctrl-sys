package com.binghu.risk.flink.redis.conf;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum RedisDataType {
    STRING,
    HASH,
    LIST,
    SET,
    SORTED_SET,
    ;
}
