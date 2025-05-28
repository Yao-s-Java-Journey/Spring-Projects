package com.manager.controller;

import com.manager.entity.PageBean;
import com.manager.entity.Result;
import com.manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public Result page(Integer page, Integer pageSize) {
        log.info("分页查询：page -> {}, pageSize -> {}", page, pageSize);
        PageBean pageBean = employeeService.page(page, pageSize);
        return Result.success(pageBean);
    }
}
