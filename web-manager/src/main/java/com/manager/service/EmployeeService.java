package com.manager.service;

import com.manager.entity.Employee;
import com.manager.entity.EmployeeQueryParam;
import com.manager.entity.PageBean;

public interface EmployeeService {
    PageBean page(Integer page, Integer pageSize);
    PageBean list(Integer page, Integer pageSize);
    PageBean queryPage(EmployeeQueryParam params);
    void save(Employee employee);
}
