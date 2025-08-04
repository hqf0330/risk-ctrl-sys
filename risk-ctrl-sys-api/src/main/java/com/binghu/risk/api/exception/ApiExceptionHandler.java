package com.binghu.risk.api.exception;

import com.binghu.risk.common.custom.RedisException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕捉
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = RedisException.class)
    public void RedisExceptionHandler(RedisException e) {
        //TODO
        // 将错误信息进行返回
    }
}
