package com.manager.controller;

import com.manager.entity.Employee;
import com.manager.entity.LoginInfo;
import com.manager.entity.Result;
import com.manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public Result login(@RequestBody Employee emp) {
        // 查询用户和密码
        LoginInfo loginInfo = employeeService.getByAcctAndPwd(emp.getAccount(), emp.getPassword());
        if (loginInfo == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.success(loginInfo);
    }
}
