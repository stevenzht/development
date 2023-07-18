package com.atguigu5.dbutils;

import com.atguigu2.bean.Customer;
import com.atguigu4.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * commons-dbutils 是 Apache 组织提供的一个开源JDBC工具类库，封装了针对于数据库的增删改查操作
 * @author steven
 * @create 2023-06-13 21:27
 */
public class QueryRunnerTest {

    //测试插入
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            int insertCount = runner.update(conn, sql, "周周啊", "zhouzhou@com", "1999-01-27");
            System.out.println(insertCount);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    //测试查询

    /**
     * BeanHandler：是ResultSetHandler接口的实现类，用于封装表中的一条记录
     * @throws SQLException
     */
    @Test
    public void testQuery1() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(conn, sql, handler, 23);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    /**
     * BeanListHandler：是ResultSetHandler接口的实现类，用于封装表中的多条记录构成的集合
     * @throws SQLException
     */
    @Test
    public void testQuery2() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";

            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);

            List<Customer> list = runner.query(conn, sql, handler, 23);
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    /**
     * MapHandler：是ResultSetHandler接口的实现类，对应表中的一条记录
     * 将字段及相应字段的值作为map中的key和value
     * @throws SQLException
     */
    @Test
    public void testQuery3() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, handler, 21);
            System.out.println(map);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    /**
     * MapListHandler：是ResultSetHandler接口的实现类，对应表中的多条记录
     * 将字段及相应字段的值作为map中的key和value，将这些map添加到List中
     * @throws SQLException
     */
    @Test
    public void testQuery4() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(conn, sql, handler, 21);
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    @Test
    public void testQuery5() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();

            String sql = "select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();

            Long count = (Long)runner.query(conn, sql, handler);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    /**
     * ScalarHandler：用于查询特殊值
     */
    @Test
    public void testQuery6() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();

            String sql = "select max(birth) from customers";
            ScalarHandler handler = new ScalarHandler();

            Date maxBirth = (Date) runner.query(conn, sql, handler);
            System.out.println(maxBirth);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    /**
     * 自定义是ResultSetHandler的实现类
     */
    @Test
    public void testQuery7() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();

            String sql = "select id,name,email,birth from customers where id = ?";
            ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
                @Override
                public Customer handle(ResultSet rs) throws SQLException {
//                    return null;

                    if (rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        Date birth = rs.getDate("birth");
                        Customer customer = new Customer(id,name,email,birth);
                        return customer;
                    }
                    return null;
                }
            };

            Customer customer = runner.query(conn, sql, handler, 21);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }
}
