package com.manager.mapper;

import com.manager.entity.Dept;
import com.manager.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 方式一：数据库字段名与实体类属性名映射
    // @Results({
    //         @Result(column = "create_time", property = "createTime")
    //         @Result(column = "update_time", property = "updateTime")
    // })
    public List<Dept> getAllDepts();
}
