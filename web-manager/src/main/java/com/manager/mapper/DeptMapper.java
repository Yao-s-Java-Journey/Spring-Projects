package com.manager.mapper;

import com.manager.entity.Dept;
import com.manager.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 方式一：数据库字段名与实体类属性名映射
    // @Results({
    //         @Result(column = "create_time", property = "createTime")
    //         @Result(column = "update_time", property = "updateTime")
    // })
    List<Dept> getAllDepts();

    /**
     * 根据部门 id 删除部门
     * @param id
     */
    @Delete("delete from department where id = #{id}")
    void deleteById(int id);

    /**
     * 创建单个部门
     * @param dept
     */
    @Insert("insert into department(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void create(Dept dept);

    /**
     * 根据 id 查询部门信息
     * @param id
     * @return
     */
    @Select("select * from department where id = #{id}")
    Dept getById(int id);
}
