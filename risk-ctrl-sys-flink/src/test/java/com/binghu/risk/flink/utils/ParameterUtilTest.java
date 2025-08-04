package com.binghu.risk.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterUtilTest {

    @DisplayName("测试获取环境参数")
    @Test
    void testGetParameter() throws IOException {
        ParameterTool parameter = ParameterUtil.getParameter("flink.properties");
        assertEquals("test", parameter.get("test"));
    }
}
