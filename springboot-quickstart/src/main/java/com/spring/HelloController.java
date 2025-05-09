package com.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求处理类
 */
@RestController // 表示当前类是一个请求处理类
public class HelloController {
    @RequestMapping("/hello") // 标识请求路径
    public String sayHello() {
        return "Hello World";
    }
}
