package com.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String account;
    private String password;
    private String username;
    private int gender; // 1- 男，2-女
    private String phone;
    private int job; // 1-班主任，2-讲师，3-学工主管，4-教研主管，5-咨询师
    private LocalDateTime entryDate; // 入职时间
}
