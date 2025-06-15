package com.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.manager.entity.*;
import com.manager.mapper.EmployeeMapper;
import com.manager.mapper.ExperienceMapper;
import com.manager.service.EmpLogService;
import com.manager.service.EmployeeService;
import com.manager.utils.JWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ExperienceMapper expMapper;

    @Autowired
    private EmpLogService empLogService;

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

    /**
     * 完整的条件分页查询
     *
     * @param params 查询条件
     * @return 当前页查询数据
     */
    @Override
    public PageBean queryPage(EmployeeQueryParam params) {
        // 1. 配置分页参数（只会对紧跟其后的第一条 SQL 语句进行分页处理，如果其他 SQL 需要分页，就在对应的 SQL 语句上再写一次）
        PageHelper.startPage(params.getPage(), params.getPageSize());

        // 2. 调用 mapper 查询
        List<Employee> res = employeeMapper.queryPage(params);

        // 3. 封装 PageBean 对象并返回结果
        Page list = (Page) res;
        return new PageBean(list.getTotal(), list.getResult());
    }

    /**
     * 新增员工
     *
     * @param employee
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional // 开启事务管理
    public void save(Employee employee) {
        try {
            // 1. 保存员工基本信息到 employee 表
            // 1.1 补充缺失字段
            employee.setPassword("123456");
            employee.setCreateTime(LocalDateTime.now());
            employee.setUpdateTime(LocalDateTime.now());
            // 1.2 调用 mapper
            employeeMapper.insert(employee);

            // 2. 保存员工经历信息到 experience 表
            Integer id = employee.getId(); // 使用 @Options 注解才能拿到
            List<EmployeeExperience> expList = employee.getExperienceList();
            if (!CollectionUtils.isEmpty(expList)) {
                // 2.1 经历关联id
                expList.forEach(exp -> {
                    exp.setEmployeeId(id);
                });
                // 2.2 批量保存员工经历
                expMapper.insertBatch(expList);
            }
        } finally {
            // 事务管理：无论新增员工是否成功，都需要添加操作日志
            EmployeeLog empLog = new EmployeeLog();
            empLog.setOperateTime(LocalDateTime.now());
            empLog.setInfo("插入员工：" + employee.getUsername());
            empLogService.insertLog(empLog);
        }
    }

    /**
     * 批量删除员工
     */
    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        log.info("===== ids: {}", ids);
        // 1. 删除员工基本信息
        employeeMapper.deleteBatch(ids);

        // 2. 删除员工
        expMapper.deleteBatch(ids);
    }

    /**
     * 根据 id 查询员工
     *
     * @param id
     * @return
     */
    @Override
    public Employee getById(Integer id) {
        // 方式一：分步查询（简单，推荐）
        // 1. 先查询员工基本信息
        // Employee emp =  employeeMapper.getBasicById(id);
        // 2. 再查询员工经历信息
        // emp.setExperienceList(expMapper.getByEmpId(id));

        // 方式二：多表外连查询
        Employee emp = employeeMapper.getById(id);

        return emp;
    }

    /**
     * 修改员工信息
     *
     * @param employee
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void update(Employee employee) {
        // 1. 修改员工基本信息
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.update(employee);

        // 2. 修改员工经历信息（直接覆盖，即先删后增）
        Integer empId = employee.getId();
        expMapper.deleteByEmpId(empId);
        // 2.2 新的经历需要关联 employee_id
        List<EmployeeExperience> expList = employee.getExperienceList();
        if (!CollectionUtils.isEmpty(expList)) {
            expList.forEach(exp -> {
                exp.setEmployeeId(empId);
            });
            expMapper.insertBatch(expList);
        }
    }

    /**
     * 根据账号和密码查询员工
     *
     * @param account  账号
     * @param password 密码
     * @return 员工信息
     */
    @Override
    public LoginInfo getByAcctAndPwd(String account, String password) {
        Employee emp = employeeMapper.getByAcctAndPwd(account, password);

        // 查询到员工，为其创建 token
        if (emp != null) {
            // 生成 jwt 负载
            Integer id = emp.getId();
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", id);
            claims.put("username", emp.getUsername());
            // 创建 token
            String accessToken = JWT.createToken(claims);
            // 返回登录信息
            return new LoginInfo(id, emp.getAccount(), emp.getUsername(), accessToken);
        }
        return null;
    }
}
