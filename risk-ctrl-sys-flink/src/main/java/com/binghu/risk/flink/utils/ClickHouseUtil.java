package com.binghu.risk.flink.utils;

import com.binghu.risk.flink.clickhouse.sink.ClickHouseJdbcSink;
import com.binghu.risk.flink.clickhouse.source.ClickHouseSource;
import com.binghu.risk.model.CHTestPO;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.IOException;

/**
 * clickhouse的读取和写入
 * @param <T>
 */
public class ClickHouseUtil<T> {
    private static String URL = null;

    static {
        ParameterTool params = null;
        try {
            params = ParameterUtil.getParameter("flink.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        URL = params.get("clickhouse.url");
    }

    /**
     * 创建clickhouse的source
     * @param env
     * @param sql
     * @return
     */
    public static DataStream<CHTestPO> read(StreamExecutionEnvironment env, String sql) {
        return env.addSource(new ClickHouseSource(URL, sql));
    }

    public static <T>DataStreamSink<T> batchWrite(DataStream<T> dataStream, String sql, Integer batchSize) {
        ClickHouseJdbcSink<T> clickHouseJdbcSink = new ClickHouseJdbcSink<>(sql, batchSize, URL);
        return dataStream.addSink(clickHouseJdbcSink.getSink());
    }

}
