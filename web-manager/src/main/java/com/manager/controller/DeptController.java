package com.manager.controller;

import com.manager.entity.Dept;
import com.manager.entity.Result;
import com.manager.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/depts")
    public Result create(@RequestBody Dept dept) {
        // @RequestBody 会将请求体中的数据自动填充到 dept 对应的属性上（前提是字段名相同）
        deptService.create(dept);
        return Result.success();
    }

    @GetMapping("/depts/{deptId}")
    public Result getById(@PathVariable("deptId") int id) {
        Dept data = deptService.getById(id);
        return Result.success(data);
    }

    @PutMapping("/depts/{deptId}")
    public Result update(@RequestBody Dept dept, @PathVariable("deptId") int id) {
        System.out.println("dept = " + dept);
        deptService.update(id, dept);
        return Result.success();
    }
}
