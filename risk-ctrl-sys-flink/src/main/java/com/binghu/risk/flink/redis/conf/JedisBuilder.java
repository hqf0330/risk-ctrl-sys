package com.binghu.risk.flink.redis.conf;

import redis.clients.jedis.JedisCluster;

/**
 * jedis的封装工具，可以继续扩展
 * todo
 */
public class JedisBuilder {

    private JedisCluster jedisCluster = null;

    public JedisBuilder(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public void close() {
        this.jedisCluster.close();
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public String set(String key,String value) {
        return jedisCluster.set(key,value);
    }


}
