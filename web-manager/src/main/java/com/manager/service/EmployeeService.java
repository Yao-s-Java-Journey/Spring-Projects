package com.manager.service;

import com.manager.entity.Employee;
import com.manager.entity.EmployeeQueryParam;
import com.manager.entity.LoginInfo;
import com.manager.entity.PageBean;

import java.util.List;

public interface EmployeeService {
    PageBean page(Integer page, Integer pageSize);
    PageBean list(Integer page, Integer pageSize);
    PageBean queryPage(EmployeeQueryParam params);
    void save(Employee employee);
    void delete(List<Integer> ids);
    Employee getById(Integer id);
    void update(Employee employee);
    LoginInfo getByAcctAndPwd(String account, String password);
}
