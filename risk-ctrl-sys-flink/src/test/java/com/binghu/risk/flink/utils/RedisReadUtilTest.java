package com.binghu.risk.flink.utils;

import com.binghu.risk.flink.redis.conf.RedisCommand;
import com.binghu.risk.model.RedisPO;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RedisReadUtilTest {

    @DisplayName("测试自定义redis source读取string类型的数据")
    @Test
    void testReadByCustomSourceWithString() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment().setParallelism(1);
        DataStream<RedisPO> ds = RedisReadUtil.read(env, RedisCommand.GET, "testKey");
        ds.print();
        env.execute();
    }
}
