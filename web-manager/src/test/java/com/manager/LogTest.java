package com.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("Logback 日志测试")
public class LogTest {
    public static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    // @ParameterizedTest
    // @CsvSource({
    //         "1, 2",
    //         "1, 0"
    // })
    // public void testLogger(int a, int b) {
    @Test
    public void testLogger() {
        try {
            logger.info("开始记录");
            division(1, 2);
            logger.info("执行结束");
        } catch (Exception e) {
            logger.error("错误日志：" + e.getMessage());
        }
    }

    public static void division(int a, int b) {
        logger.debug("调试日志：" + a + "/" + b);
        double c = a / b;
        System.out.println("c = " + c);
        logger.info("计算结果： " + c);
    }
}
