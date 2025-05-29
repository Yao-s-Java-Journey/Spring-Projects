package com.manager.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 员工履历
 */
@Data
public class EmployeeExperience {
    private String company; // 公司
    private String job; // 职位
    private LocalDate begin;
    private LocalDate end;
}
