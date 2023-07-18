package com.atguigu4.connection;

import com.atguigu1.util.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author steven
 * @create 2023-06-13 19:02
 */
public class C3P0Test {
    @Test
    public void testGetConnection() throws Exception {
        //方式一：
        //获取C3P0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" );
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("123456");

        //通过设置相关的参数，对数据库连接池进行管理
        //设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);

        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);

        //销毁C3P0数据库连接池
        //DataSources.destroy( cpds );
    }
    //方式二：使用配置文件
    @Test
    public void testGetConnection1() throws SQLException {

        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();

    }
}
