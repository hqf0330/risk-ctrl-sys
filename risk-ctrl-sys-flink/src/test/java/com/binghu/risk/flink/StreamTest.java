package com.binghu.risk.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.operators.StreamFlatMap;
import org.apache.flink.streaming.util.KeyedOneInputStreamOperatorTestHarness;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StreamTest {

    @DisplayName("无状态算子单元测试")
    @Test
    public void testNoStateful() throws Exception {
        TestMapFunction statelessMap = new TestMapFunction();
        String out = statelessMap.map("world");
        System.out.println(out);

        Assert.assertEquals("hello world", out);
    }

    @DisplayName("有状态算子单元测试")
    @Test
    public void testStateful() throws Exception {
        KeyedOneInputStreamOperatorTestHarness<String, Tuple2<String, Integer>, Tuple2<String, Integer>> testStateful = new KeyedOneInputStreamOperatorTestHarness<>(
                new StreamFlatMap<>(new TestStatefulFunction()),
                x -> x.f0,
                Types.STRING
        );

        testStateful.open();

        testStateful.processElement(Tuple2.of("hadoop", 1), 100L);
        testStateful.processElement(Tuple2.of("hadoop", 4), 102L);

        System.out.println(testStateful.extractOutputValues());

        Assert.assertEquals(
                testStateful.extractOutputValues(),
                Arrays.asList(Tuple2.of("hadoop", 1), Tuple2.of("hadoop", 5))
        );
    }
}
