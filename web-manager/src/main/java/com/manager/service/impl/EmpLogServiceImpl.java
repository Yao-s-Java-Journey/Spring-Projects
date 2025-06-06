package com.manager.service.impl;

import com.manager.entity.EmployeeLog;
import com.manager.mapper.EmpLogMapper;
import com.manager.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {
    @Autowired
    private EmpLogMapper empLogMapper;

    /**
     * 插入操员工作日志
     * @param empLog
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 总是开启一个新事务（不受外层事务影响）
    @Override
    public void insertLog(EmployeeLog empLog) {
        empLogMapper.insert(empLog);
    }
}
