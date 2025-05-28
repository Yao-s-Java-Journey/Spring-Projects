package com.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@DisplayName("接入JDBC")
public class JDBCTest {
    @DisplayName("快速开始")
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        // 1. 加载 MySQL 驱动（旧方式，现代 JDBC 通常不需要显式调用）
        Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 驱动的全限定名

        // 2. 获取数据库连接对象 Connection
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/manager", // 数据库地址
                "root",
                "1234"
        );

        // 3. 获取 SQL 语句执行对象
        Statement statement = connection.createStatement();

        // 4. 执行 SQL 语句
        // 该方法返回 int 值，表示影响的行数，根据返回值验证执行结果。
        int row = statement.executeUpdate("update employee set account = '落叶树' where id = 1");
        System.out.println("row = " + row);

        // 5. 释放资源
        statement.close();
        connection.close();
    }
}
