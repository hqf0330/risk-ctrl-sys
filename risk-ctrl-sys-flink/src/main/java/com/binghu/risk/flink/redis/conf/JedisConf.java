package com.binghu.risk.flink.redis.conf;

import com.binghu.risk.flink.utils.ParameterUtil;
import org.apache.flink.api.java.utils.ParameterTool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;

public class JedisConf {

    public static JedisCluster getJedisCluster() throws IOException {
        ParameterTool params = ParameterUtil.getParameter("flink.properties");
        String host = params.get("redis.host");
        String port = params.get("redis.port");
        String password = params.get("redis.password");

        /**
         * jedisCluster是集群模式
         * jedisPool是单机版模式
         */
        HashSet<HostAndPort> nodes = new HashSet<>();
        HostAndPort nodeMaster = new HostAndPort(host, Integer.parseInt(port));
        // 如果有其他节点，可以直接add就行
        nodes.add(nodeMaster);

        return new JedisCluster(nodes, 2000, 2000, 5, password, null);
    }
}
