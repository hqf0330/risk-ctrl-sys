package com.binghu.risk.flink.redis.source;

import com.binghu.risk.flink.redis.conf.JedisBuilder;
import com.binghu.risk.flink.redis.conf.JedisConf;
import com.binghu.risk.flink.redis.conf.RedisCommand;
import com.binghu.risk.model.RedisPO;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import redis.clients.jedis.JedisCluster;

/**
 * RichParallelSourceFunction 支持多并行度 对比一下RichSourceFunction
 */
public class RedisSource extends RichParallelSourceFunction<RedisPO> {

    /**
     * jedis对象
     */
    private JedisBuilder jedisBuilder;

    /**
     * redis命令
     */
    private RedisCommand redisCommand;

    /**
     * redis读取的key
     */
    private final String key;

    /**
     * 当该变量被修改的时候，会通知到其他的线程
     */
    private volatile boolean isRunning = true;

    public RedisSource(RedisCommand redisCommand, String key) {
        this.redisCommand = redisCommand;
        this.key = key;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        JedisCluster jedisCluster = JedisConf.getJedisCluster();
        jedisBuilder = new JedisBuilder(jedisCluster);
    }

    @Override
    public void run(SourceContext<RedisPO> output) throws Exception {
        String data = null;
        while (isRunning) {
            switch (redisCommand.getRedisDataType()) {
                case STRING:
                    data = jedisBuilder.get(key);
            }
            output.collect(new RedisPO(data));
        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;
    }
}
