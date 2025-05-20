package com.manager.service;

import com.manager.entity.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
    List<Dept> getAll();

    /**
     * 根据 id 删除部门
     * @param id @Override
     */
    void deleteById(int id);

    /**
     * 创建部门
     * @param dept
     */
    void create(Dept dept);

    /**
     * 获取单个部门信息
     * @param id 部门 id
     * @return
     */
    Dept getById(int id);
}
