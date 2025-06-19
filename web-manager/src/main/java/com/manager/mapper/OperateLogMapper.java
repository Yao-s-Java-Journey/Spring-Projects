package com.manager.mapper;

import com.manager.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    void insert(OperateLog operateLog);
}
