package com.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.util.Args;

import java.time.LocalDateTime;

/**
 * 操作日志
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog {
    private Integer id;
    private Integer operateUserId;
    private LocalDateTime operateTime;
    private String operateClass;
    private String operateMethod;
    private String methodArgs;
    private String returnValue;
    private Long costTime;
}
