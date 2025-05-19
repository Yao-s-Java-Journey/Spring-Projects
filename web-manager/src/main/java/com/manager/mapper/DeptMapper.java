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
}
