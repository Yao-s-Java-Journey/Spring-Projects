package com.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
     *
     * @param page     第几页
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

    /**
     * 使用 PageHelper 的分页查询
     *
     * @param page     第几页
     * @param pageSize 每页查询条数
     * @return 当前页查询数据
     */
    @Override
    public PageBean list(Integer page, Integer pageSize) {
        // 1. 配置分页参数（只会对紧跟其后的第一条 SQL 语句进行分页处理，如果其他 SQL 需要分页，就在对应的 SQL 语句上再写一次）
        PageHelper.startPage(page, pageSize);

        // 2. 调用 mapper 查询
        List<Employee> res = employeeMapper.list();

        // 3. 封装 PageBean 对象并返回结果
        Page list = (Page) res;
        return new PageBean(list.getTotal(), list.getResult());

        // 还可以有以下写法：
        // return new PageBean(list.getTotal(), list);
        // return new PageBean(list.getTotal(), res);
    }
}
