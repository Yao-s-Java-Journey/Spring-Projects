package com.manager.filter;

import com.manager.utils.JWT;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 登录过滤器
 */
@Slf4j
// @WebFilter("/*") // urlPatterns 和 values 作用一样，可以只写一个
public class LoginFilter implements Filter{
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        // 1. 强转 request 和 response（以便使用部分方法）
        HttpServletRequest  req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 2. 获取 uri，判断登录接口
        String uri = req.getRequestURI(); // uri 和 url 的区别：url 包含了协议、域名、端口号，uri 只包含路径
        if (uri.contains("login")) {
            // 2.1 登录接口直接放行
            chain.doFilter(request, response);
        } else {
            // 2.2 其他接口进行 token 登录验证
            String token = req.getHeader("Authorization");

            // 2.3 令牌非空判断
            if (token == null || token.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            };

            // 2.4 解析令牌
            try  {
                Claims claims = JWT.parseToken(token);
                log.info("=== claims === {}", claims);
                chain.doFilter(request, response);
            } catch (Exception e) {
                log.error("解析出错了：{}", e.getMessage());
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
