package com.manager.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private int id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}