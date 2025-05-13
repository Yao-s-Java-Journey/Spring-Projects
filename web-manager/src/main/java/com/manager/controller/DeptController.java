package com.manager.controller;

import com.manager.entity.Result;
import com.manager.service.DeptService;
import com.manager.service.DeptServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求处理类
 */
@RestController
public class DeptController {
    private DeptService deptService = new DeptServiceImpl();

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result getAll() {
        // 3. 返回 JSON 响应
        return Result.success(deptService.getAll());
    }
}
