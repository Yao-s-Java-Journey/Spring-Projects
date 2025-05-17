package com.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;

@DisplayName("测试 JDBC ResultSet")
public class ResultSetTest {
    @DisplayName("从 ResultSet 获取信息")
    @ParameterizedTest // 参数化测试
    @CsvSource({"我是一棵石榴树, 123456", "我是一棵榴莲树, 123456"})
    public void testResultSet(String account, String pwd) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql:///users",
                "root",
                "1234"
        );
        Statement statement = connection.createStatement();

        // 查询符合条件的用户
        ResultSet data = statement.executeQuery("select * from employee where account = '" + account + "' and password = '" + pwd + "'");
        // 判断是否有值
        while (data.next()) {
            // 解析
            int id = data.getInt("id");
            System.out.println("找到该用户，id 为" + id);
            // String accountStr = data.getString("account");
            // int gender = data.getInt("gender");;
            // String username = data.getString("username");
            // String phone = data.getString("phone");
            // int job = data.getInt("job");
            // 返回用户实体类......
        }

        statement.close();
        connection.close();
    }
}
