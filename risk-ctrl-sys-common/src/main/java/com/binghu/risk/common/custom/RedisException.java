package com.binghu.risk.common.custom;

import com.binghu.risk.common.BizRuntimeException;
import com.binghu.risk.common.enums.BizExceptionInfo;

public class RedisException extends BizRuntimeException {

    public RedisException(BizExceptionInfo info) {
        super(info);
    }
}
