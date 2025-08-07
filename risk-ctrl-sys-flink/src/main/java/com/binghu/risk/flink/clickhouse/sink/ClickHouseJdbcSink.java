package com.binghu.risk.flink.clickhouse.sink;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * jdbc connector + 批量写入 + 预编译SQL
 */
public class ClickHouseJdbcSink<T> {

    private final SinkFunction<T> sink;

    private final static String NA = "null";

    public ClickHouseJdbcSink(String sql, Integer batchSize, String url) {
        sink = JdbcSink.sink(
                sql
                , new ClickHouseJdbcStatementBuilder<T>()
                // 设置批量大小
                , JdbcExecutionOptions.builder()
                        .withBatchSize(batchSize)
                        .build()
                // 设置clichouse的链接配置
                , new JdbcConnectionOptions.JdbcConnectionOptionsBuilder().withUrl(url).build()
        );
    }

    public SinkFunction<T> getSink() {
        return this.sink;
    }

    public static void setPreparedStatement(
            PreparedStatement ps,
            Field[] fields,
            Object object) throws IllegalAccessException, SQLException {

        //遍历 Field[]
        for (int i = 1; i <= fields.length; i++) {
            //取出每个Field实例
            Field field = fields[i - 1];
            //指示反射的对象在使用时应该取消 Java 语言访问检查
            field.setAccessible(true);
            //通过Field实例的get方法返回指定的对象
            Object o = field.get(object);
            if (o == null) {
                ps.setNull(i, 0);
                continue;
            }

            //这里统一设为字符型
            String fieldValue = o.toString();

            //判断字符串是否为null或者空
            if (!NA.equals(fieldValue) && !"".equals(fieldValue)) {
                //替换对应位置的占位符
                ps.setObject(i, fieldValue);
            } else {
                ps.setNull(i, 0);
            }
        }
    }
}
