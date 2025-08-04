package com.binghu.risk.common.enums;

import lombok.Getter;

@Getter
public enum RedisExceptionInfo implements BizExceptionInfo{

    REDIS_EXCEPTION_INFO("-300", "RedisTemplate对象为null");
    private String exceptionCode;
    private String exceptionMsg;

    RedisExceptionInfo(String exceptionCode, String exceptionMessage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMessage;
    }
}
