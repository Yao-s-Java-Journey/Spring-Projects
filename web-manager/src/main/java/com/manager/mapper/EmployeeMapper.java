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
    List<Employee> getAllEmployees();

    /**
     * 统计所有员工数量
     * @return 所有员工数 total
     */
    @Select("select count(*) from employee")
    Long count();

    /**
     * 分页查询员工数据
     * @param start 查询页数
     * @return
     */
    @Select("select e.*, d.name as deptName from employee as e inner join department as d on e.dept_id = d.id limit #{start}, #{pageSize}")
    List<Employee> page(Integer start, Integer pageSize);
}
