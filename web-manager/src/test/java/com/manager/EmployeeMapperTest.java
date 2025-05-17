package com.manager;

import com.manager.entity.Employee;
import com.manager.mapper.EmployeeMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@DisplayName("测试 Mybatis")
@SpringBootTest // 作用：会加载 Spring 环境（IOC 容器）
public class EmployeeMapperTest {
    @Autowired
    private EmployeeMapper mapper;

    @DisplayName("查询所有员工")
    @Test
    public void test() {
        List<Employee> employeeList = mapper.getAllEmployees();
        // System.out.println("employeeList：" + employeeList);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
