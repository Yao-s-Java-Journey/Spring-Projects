package com.manager.controller;

import com.manager.entity.Dept;
import com.manager.entity.Result;
import com.manager.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 请求处理类
 */
@RestController
public class DeptController {
    // @Autowired // 自动从 IOC 容器中寻找 Bean 对象，为该变量赋值
    // @Qualifier("service2") // 指定 Bean 对象
    // @Resource(name = "service2") // JDK 自带，默认按 Bean 的首字母小写名称注入（也可根据 @Service 指定的名字注入）
    @Resource(name="deptService")
    private DeptService deptService;

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result getAll() {
        // 3. 返回 JSON 响应
        List<Dept> data = deptService.getAll();
        return Result.success(data);
    }

    @DeleteMapping("/depts")
    public Result deleteById(@RequestParam("deptId") int id) {
        deptService.deleteById(id);
        return Result.success();
    }
}
