package com.manager.mapper;

import com.manager.entity.EmployeeLog;
import org.apache.ibatis.annotations.Insert;

public interface EmpLogMapper {
    @Insert("insert into emp_log (operate_time, info) values (#{operateTime}, #{info})")
    public void insert(EmployeeLog empLog);
}
