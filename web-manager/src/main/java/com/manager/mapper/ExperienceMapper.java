package com.manager.mapper;

import com.manager.entity.EmployeeExperience;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExperienceMapper {
    /**
     * 批量新增员工经历
     */
    void insertBatch(List<EmployeeExperience> expList);
}
