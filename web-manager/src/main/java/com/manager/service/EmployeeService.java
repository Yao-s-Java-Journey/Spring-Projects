package com.manager.service;

import com.manager.entity.PageBean;

public interface EmployeeService {
    PageBean page(Integer page, Integer pageSize);
    PageBean list(Integer page, Integer pageSize);
}
