package com.manager.service.impl;

import com.manager.entity.Dept;
import com.manager.mapper.DeptMapper;
import com.manager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
