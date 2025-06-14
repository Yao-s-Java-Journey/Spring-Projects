package com.manager.mapper;

import com.manager.entity.Employee;
import com.manager.entity.EmployeeQueryParam;
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

    /**
     * 基于 XML 开发的完整的动态条件分页查询
     * @param params 查询条件
     * @return 查询结果
     */
    List<Employee> queryPage(EmployeeQueryParam params);

    /**
     * 新增员工
     * @param emp 员工信息
     */
    void insert(Employee emp);

    /**
     * 批量删除员工
     * @param ids
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据员工 id 查询员工基本信息
     * @param id
     */
    @Select("select * from employee where id = #{id}")
    Employee getBasicById(Integer id);

    /**
     * 根据员工 id 查询员工所有信息（包括连表查询就职经历）
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 修改员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据员工账号和密码查询员工信息
     * @param acct  员工账号
     * @param pwd  员工密码
     */
    @Select("select * from employee where account = #{acct} and password = #{pwd}")
    Employee getByAcctAndPwd(String acct, String pwd);
}
