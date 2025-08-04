package com.binghu.risk.flink;

import org.apache.flink.api.common.functions.MapFunction;

/**
 * 无状态测试算子
 */
public class TestMapFunction implements MapFunction<String, String> {
    @Override
    public String map(String s) throws Exception {
        return "hello " + s;
    }
}
