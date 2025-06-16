package com.manager.interceptor;

import com.manager.utils.JWT;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        // TODO: 请求到达 Controller 之前，可以在这里进行权限校验、日志记录等操作

        // 登录接口直接放行（这一步直接在拦截器配置中完成）
        // String uri = request.getRequestURI();
        // if (uri.contains("login")) return true;

        // 获取请求头中的 token
        String token = request.getHeader("Authorization");

        // token 非空判断
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        };

        // 令牌解析
        try  {
            Claims claims = JWT.parseToken(token);
            log.info("=== claims === {}", claims);
        } catch (Exception e) {
            log.error("token 解析错误：{}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 返回 true，继续执行后续的拦截器和 Controller 方法（最终 token 解析成功才会走这里）
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {
        System.out.println("MyInterceptor postHandle: Controller 方法执行完成，视图渲染之前");
        // TODO: 可以在这里对模型数据进行处理
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws Exception {
        System.out.println("MyInterceptor afterCompletion: 请求处理完成，视图渲染之后");
        // TODO: 可以在这里进行资源清理、日志记录等操作
    }
}