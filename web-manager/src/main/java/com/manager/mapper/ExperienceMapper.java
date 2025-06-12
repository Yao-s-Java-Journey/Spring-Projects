package com.manager.mapper;

import com.manager.entity.EmployeeExperience;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExperienceMapper {
    /**
     * 批量新增员工经历
     */
    void insertBatch(List<EmployeeExperience> expList);

    /**
     * 批量删除员工经历
     * @param empIds
     */
    void deleteBatch(List<Integer> empIds);

    /**
     * 根据员工id查询员工经历
     * @param empId
     */
    @Select("select * from experience where employee_id = #{empId}")
    List<EmployeeExperience> getByEmpId(Integer empId);

    /**
     * 批量更新员工经历
     * @param experienceList
     */
    void update(List<EmployeeExperience> experienceList);

    /**
     * 根据员工 id 删除员工经历
     * @param empId
     */
    void deleteByEmpId(Integer empId);
}
