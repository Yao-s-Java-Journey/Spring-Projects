package com.manager.aop;

import com.manager.entity.OperateLog;
import com.manager.mapper.OperateLogMapper;
import com.manager.utils.JWT;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 记录操作日志的切面类
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    /**
     * 拦截 @Loggable 注解，为其提供所需地日志信息
     */
    @Around("@annotation(com.manager.anno.Loggable)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 用户 id（从 token 中获取）
        String token = request.getHeader("Authorization");
        Claims claims = JWT.parseToken(token);
        Integer operateUserId = (Integer) claims.get("id");

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String operateClass = joinPoint.getTarget().getClass().getName();

        // 操作方法名
        String operateMethod = joinPoint.getSignature().getName();

        // 操作方法参数
        String methodArgs = Arrays.toString(joinPoint.getArgs());

        // 调用原始方法
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 返回值
        String returnValue = result == null ? "null" : result.toString();

        // 操作耗时
        Long costTime = end - begin;

        // 插入数据库
        OperateLog operateLog = new OperateLog(
                null, operateUserId, operateTime, operateClass, operateMethod, methodArgs, returnValue, costTime
        );
        operateLogMapper.insert(operateLog);

        return result;
    }
}
