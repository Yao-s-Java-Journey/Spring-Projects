package com.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String account;
    private String password;
    private String username;
    private Integer gender; // 1- 男，2-女
    private String phone;
    private Integer deptId;
    private Integer job; // 1-班主任，2-讲师，3-学工主管，4-教研主管，5-咨询师
    private Double salary; // 薪资
    private String avatar; // 头像链接
    private LocalDate entryDate; // 入职时间
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    // 从 department 表中查询到的部门名称
    private String deptName;

    // 从 experience 表中查询到的员工经历
    private List<EmployeeExperience> experienceList;
}
