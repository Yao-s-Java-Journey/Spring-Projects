package com.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;

@DisplayName("PreparedStatement 预编译")
public class PreparedStatementTest {
    @DisplayName("防止 SQL 注入")
    @ParameterizedTest
    @CsvSource({"我是一棵石榴树, 123456", "asd&asd, ' or '1' = 1'"})
    public void testPreparedStatement(String account, String pwd) throws SQLException  {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql:///users",
                "root",
                "1234"
        );

        // 1. 创建 PreparedStatement对象，预编译 SQL 语句并将其存储在 PreparedStatement 对象中，'?' 是占位符
        PreparedStatement ps = connection.prepareStatement("select * from employee where account = ? and password = ?");

        // 2. 设置 ? 的参数值
        ps.setString(1, account); // 第一个 ? 占位符
        ps.setString(2, pwd); // 第二个 ? 占位符

        // 3. 执行 SQL 语句
        ResultSet data = ps.executeQuery();
        while (data.next()) {
            String accountStr = data.getString("account");
            int gender = data.getInt("gender");
            Date createTime =  data.getDate("create_time");
            System.out.println("账户：" + accountStr + "，性别：" + gender + "，创建时间：" + createTime);
        }
    }
}
