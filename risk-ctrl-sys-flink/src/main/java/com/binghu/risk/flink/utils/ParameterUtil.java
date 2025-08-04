package com.binghu.risk.flink.utils;

import org.apache.flink.api.java.utils.ParameterTool;

import java.io.IOException;
import java.io.InputStream;

public class ParameterUtil {

    public static ParameterTool getParameter(String file) throws IOException {
        InputStream inputStream = ParameterUtil.class.getClassLoader().getResourceAsStream(file);
        return ParameterTool.fromPropertiesFile(inputStream);
    }
}
