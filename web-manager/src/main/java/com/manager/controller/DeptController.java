package com.manager.controller;

import com.manager.entity.Result;
import com.manager.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求处理类
 */
@RestController
public class DeptController {
    // @Autowired // 自动从 IOC 容器中寻找 Bean 对象，为该变量赋值
    // @Qualifier("service2")
    @Resource(name = "service2") // JDK 自带，默认按 Bean 的首字母小写名称注入（也可根据 @Service 指定的名字注入）
    private DeptService deptService;

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result getAll() {
        // 3. 返回 JSON 响应
        return Result.success(deptService.getAll());
    }
}
