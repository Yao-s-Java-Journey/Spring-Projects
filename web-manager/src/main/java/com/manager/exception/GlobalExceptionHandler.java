package com.manager.exception;

import com.manager.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 捕获 Controller 控制器层抛出的所有异常
public class GlobalExceptionHandler {
    // 指定处理何种异常
    @ExceptionHandler
    // @ExceptionHandler(NullPointerException.class)
    public Result doException(Exception e) {
        log.error("服务器发生异常：{}", e.getMessage());
        return Result.error("服务器发生异常");
    }
}
