package com.manager.mapper;

import com.manager.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 作用：程序启动时，自动生成该接口的代理对象，交由 IOC 容器管理
public interface EmployeeMapper {
    // 查询所有用户数据
    // 方式一：@Select("select * from employee")
    // 方式二：XML 配置（见 resources/com/manager/mapper/EmployeeMapper.xml）
    public List<Employee> getAllEmployees();
}
