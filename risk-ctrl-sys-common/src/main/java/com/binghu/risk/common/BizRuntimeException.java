package com.binghu.risk.common;

import com.binghu.risk.common.enums.BizExceptionInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常类
 */
@Slf4j
public class BizRuntimeException extends RuntimeException {
    public BizRuntimeException(BizExceptionInfo info) {
        log.error(info.getExceptionMsg());
    }
}
