package com.manager;

import com.manager.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration // 申明当前类是一个配置类，等价于 @Component
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 指定拦截的 URL 模式
        registry.addInterceptor(loginInterceptor)
                // 拦截所有请求 /** 表示任意级任意路径，/* 表示一级任意路径，不写则默认拦截所有
                .addPathPatterns("/**")
                // 排除不需要拦截的请求
                .excludePathPatterns("/login", "/register");
    }
}
