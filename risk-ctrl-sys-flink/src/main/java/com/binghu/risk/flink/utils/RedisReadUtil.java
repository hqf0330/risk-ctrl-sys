package com.binghu.risk.flink.utils;

import com.binghu.risk.flink.redis.conf.RedisCommand;
import com.binghu.risk.flink.redis.source.RedisSource;
import com.binghu.risk.model.RedisPO;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class RedisReadUtil {

    public static DataStream<RedisPO> read(StreamExecutionEnvironment env, RedisCommand redisCommand, String key) {
        return env.addSource(new RedisSource(redisCommand, key));
    }
}
