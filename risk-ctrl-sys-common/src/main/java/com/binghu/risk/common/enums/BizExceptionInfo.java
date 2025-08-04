package com.binghu.risk.common.enums;

public interface BizExceptionInfo {
    /**
     * 获取异常错误码
     * @return
     */
    String getExceptionCode();

    /**
     * 获取异常错误信息
     * @return
     */
    String getExceptionMsg();
}
