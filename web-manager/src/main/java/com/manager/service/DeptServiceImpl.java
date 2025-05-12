package com.manager.service;

import com.manager.dao.DeptDaoImpl;
import com.manager.entity.Dept;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DeptServiceImpl {
    private final DeptDaoImpl deptDao = new DeptDaoImpl();

    public List<Dept> getAll() {
        List<String> stringDeptList = deptDao.getAll();

        // 2. 解析并封装成集合
        List<Dept> depts = stringDeptList.stream().map(s -> {
            String[] split = s.split(",");
            int id = Integer.parseInt(split[0]);
            String name = split[1];
            LocalDateTime updateTime = LocalDateTime.parse(split[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime);
        }).collect(Collectors.toList());

        return depts;
    }
}
