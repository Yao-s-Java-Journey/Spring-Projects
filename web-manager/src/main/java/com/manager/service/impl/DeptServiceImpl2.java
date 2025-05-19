package com.manager.service.impl;

import com.manager.dao.DeptDao;
import com.manager.entity.Dept;
import com.manager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

// @Component // 程序启动时，自动创建该类对象，并交由 IOC 容器管理
@Service("service2") // @Component 的衍生注解，标注为一个业务层类
public class DeptServiceImpl2 implements DeptService {
    @Autowired // 自动从 IOC 容器中寻找 Bean 对象，为该变量赋值
    private DeptDao deptDao;

    @Override
    public List<Dept> getAll() {
        List<String> stringDeptList = deptDao.getAll();

        // 2. 解析并封装成集合
        List<Dept> depts = stringDeptList.stream().map(s -> {
            String[] split = s.split(",");
            int id = Integer.parseInt(split[0]);
            String name = split[1];
            LocalDateTime createTime = LocalDateTime.parse(split[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, createTime, LocalDateTime.now());
        }).collect(Collectors.toList());

        return depts;
    }

    @Override
    public void deleteById(int id) {
    }
}
