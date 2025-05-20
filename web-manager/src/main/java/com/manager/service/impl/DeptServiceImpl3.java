package com.manager.service.impl;

import com.manager.entity.Dept;
import com.manager.mapper.DeptMapper;
import com.manager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 使用 mapper 接口的 service
 */
@Service("deptService")
public class DeptServiceImpl3 implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAll() {
        List<Dept> res = deptMapper.getAllDepts();
        System.out.println("service res = " + res);
        return res;
    }

    @Override
    public void deleteById(int id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void create(Dept dept) {
        // 1. 填充请求体没有的数据（比如 update_time，由后端自己生成）
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 2. 调用 mapper 创建新部门
        deptMapper.create(dept);
    }

    @Override
    public Dept getById(int id) {
        Dept dept = deptMapper.getById(id);
        return dept;
    }

    @Override
    public void update(int id, Dept dept) {
        dept.setId(id);
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
