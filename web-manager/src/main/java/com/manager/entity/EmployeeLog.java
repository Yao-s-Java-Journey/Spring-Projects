package com.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 员工日志
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLog {
    private Integer id;
    private LocalDateTime operateTime;
    private String info;
}
