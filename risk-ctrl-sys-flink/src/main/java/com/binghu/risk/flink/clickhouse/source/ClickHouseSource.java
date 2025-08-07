package com.binghu.risk.flink.clickhouse.source;

import com.binghu.risk.model.CHTestPO;
import com.clickhouse.jdbc.ClickHouseConnection;
import com.clickhouse.jdbc.ClickHouseDataSource;
import com.clickhouse.jdbc.ClickHouseStatement;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.sql.ResultSet;
import java.util.Properties;

public class ClickHouseSource implements SourceFunction<CHTestPO> {

    private String URL;

    private String SQL;

    public ClickHouseSource(String URL, String SQL) {
        this.URL = URL;
        this.SQL = SQL;
    }

    @Override
    public void run(SourceContext<CHTestPO> output) throws Exception {
        // 持久化的属性集
        Properties props = new Properties();

        ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(URL, props);

        try(ClickHouseConnection conn = clickHouseDataSource.getConnection()) {
            ClickHouseStatement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                output.collect(new CHTestPO(rs.getString(1)));
            }
        }
    }

    @Override
    public void cancel() {

    }
}
