package com.manager.service.impl;

import com.manager.entity.Employee;
import com.manager.entity.PageBean;
import com.manager.mapper.EmployeeMapper;
import com.manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询
     * @param page 第几页
     * @param pageSize 每页查询条数
     * @return 当前页查询数据
     */
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        // 1. 调用 mapper 获取总记录数 total
        Long total = employeeMapper.count();

        // 2. 调用 mapper 获取列表分页数据 data
        Integer start = (page - 1) * pageSize; // 计算起始索引
        List<Employee> data = employeeMapper.page(start, pageSize);
        log.info("分页数据: {}", data);

        // 3. 封装 PageBean 对象
        return new PageBean(total, data);
    }
}
