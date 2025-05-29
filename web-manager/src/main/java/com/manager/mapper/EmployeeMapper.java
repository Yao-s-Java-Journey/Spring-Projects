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
     *
     * @return 所有员工数 total
     */
    @Select("select count(*) from employee")
    Long count();

    /**
     * 分页查询员工数据
     *
     * @param start 查询页数
     * @return
     */
    @Select("select e.*, d.name as deptName from employee as e inner join department as d on e.dept_id = d.id limit #{start}, #{pageSize}")
    List<Employee> page(Integer start, Integer pageSize);

    /**
     * PageHelper 分页查询
     * 无需手动使用 page、pageSize 参数和 limit
     * 只要在 service 中调用了 PageHelper.startPage()，插件就会自动拦截 SQL 语句并添加 limit
     * 主要 SQL 末尾不要加分号
     */
    @Select("select e.*, d.name as deptName from employee as e left outer join department as d on e.dept_id = d.id")
    List<Employee> list();
}
