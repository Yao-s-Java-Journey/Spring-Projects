package com.manager;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("Logback 日志测试")
@Slf4j
public class LogTest {
    // public static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    @ParameterizedTest
    @CsvSource({
            "1, 2",
            "1, 0"
    })
    public void testLogger(int a, int b) {
        try {
            log.info("开始记录");
            division(a, b);
            log.info("执行结束");
        } catch (Exception e) {
            log.error("错误日志：" + e.getMessage());
        }
    }

    public static void division(int a, int b) {
        log.debug("调试日志：" + a + "/" + b);
        double c = a / b;
        log.info("计算结果： " + c);
    }
}
