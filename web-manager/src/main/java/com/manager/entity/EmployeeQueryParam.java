package com.manager.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * employee 查询参数实体类
 */
@Data
public class EmployeeQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String account;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDateBegin; // 入职开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDateEnd; // 入职结束时间
}
