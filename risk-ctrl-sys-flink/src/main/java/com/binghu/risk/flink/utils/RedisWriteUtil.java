package com.binghu.risk.flink.utils;

import com.binghu.risk.flink.redis.sink.RedisSinkByBahirWithString;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisClusterConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

/**
 * flink写入到redis中
 * 1. 继承RedisSinkFunction
 * 2. 使用第三方包 bachir-flink
 */
public class RedisWriteUtil {

    private static FlinkJedisClusterConfig JEDIS_CONF = null;

    static {
        ParameterTool params = null;
        try {
            params = ParameterUtil.getParameter("flink.properties");
            String host = params.get("redis.host");
            String port = params.get("redis.port");

            /**
             * java套接字类型
             */
            InetSocketAddress redisAddress = new InetSocketAddress(host, Integer.parseInt(port));
            Set<InetSocketAddress> set = new HashSet<>();
            set.add(redisAddress);

            JEDIS_CONF = new FlinkJedisClusterConfig
                    .Builder()
                    .setNodes(set)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入到redis
     * @param input
     */
    public static void writeByBachirWithString(DataStream<Tuple2<String, String>> input) {
        input.addSink(new RedisSink<Tuple2<String, String>>(JEDIS_CONF, new RedisSinkByBahirWithString()));
    }
}
