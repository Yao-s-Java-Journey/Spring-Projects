package com.manager.controller;

import com.manager.entity.Employee;
import com.manager.entity.EmployeeQueryParam;
import com.manager.entity.PageBean;
import com.manager.entity.Result;
import com.manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public Result page(EmployeeQueryParam params) {
        log.info("分页查询参数：{}", params);
        // 使用原始方式分页
        // PageBean pageBean = employeeService.page(page, pageSize);

        // 使用 PageHelper 插进分页
        // PageBean pageBean = employeeService.list(page, pageSize);
        // return Result.success(pageBean);

        // 完整的条件分页查询
        PageBean pageBean = employeeService.queryPage(params);
        return Result.success(pageBean);
    }

    @PostMapping()
    public Result save(@RequestBody Employee employee) {
        log.info("新增员工：{}", employee);
        employeeService.save(employee);
        return Result.success();
    }
}
