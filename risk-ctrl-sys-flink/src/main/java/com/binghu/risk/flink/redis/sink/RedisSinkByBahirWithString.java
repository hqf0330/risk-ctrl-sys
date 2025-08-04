package com.binghu.risk.flink.redis.sink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * 基于bachir的redis sink
 */
public class RedisSinkByBahirWithString implements RedisMapper<Tuple2<String, String>> {

    /**
     * 指定的redis命令
     * @return
     */
    @Override
    public RedisCommandDescription getCommandDescription() {
        // 如果redis的数据类型是hash或者z-set，那么就要传入一个additional key
        return new RedisCommandDescription(RedisCommand.SET);
    }

    /**
     * 从流里获取value
     * @param input
     * @return
     */
    @Override
    public String getKeyFromData(Tuple2<String, String> input) {
        return input.f0;
    }

    /**
     * 从流里获取数据
     * @param input
     * @return
     */
    @Override
    public String getValueFromData(Tuple2<String, String> input) {
        return input.f1;
    }
}
