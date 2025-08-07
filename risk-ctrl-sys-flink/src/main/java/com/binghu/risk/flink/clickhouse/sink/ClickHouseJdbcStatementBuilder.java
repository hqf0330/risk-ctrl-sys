package com.binghu.risk.flink.clickhouse.sink;

import org.apache.flink.connector.jdbc.JdbcStatementBuilder;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClickHouseJdbcStatementBuilder<T> implements JdbcStatementBuilder<T> {

    @Override
    public void accept(PreparedStatement preparedStatement, T t) throws SQLException {
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            ClickHouseJdbcSink.setPreparedStatement(preparedStatement, fields, t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
