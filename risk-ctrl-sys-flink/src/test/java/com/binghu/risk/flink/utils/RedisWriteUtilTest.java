package com.binghu.risk.flink.utils;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RedisWriteUtilTest {

    @DisplayName("测试基于bahirWithString")
    @Test
    void testWriteByBahirWithString() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Tuple2<String, String> input = Tuple2.of("test:bahir", "test-value");
        DataStreamSource<Tuple2<String, String>> ds = env.fromElements(input);
        RedisWriteUtil.writeByBachirWithString(ds);

        env.execute();
    }
}
