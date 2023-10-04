package com.atguigu3.preparedstatement.crud;

import com.atguigu3.bean.Customer;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对于Customers表的查询操作
 *
 * @author steven
 * @create 2023-06-11 14:31
 */
public class CustomerForQuery {

    @Test
    public void testQueryForCustomers(){
        String sql = "select id,name,birth,email from customers where id = ?";

        System.out.println(customer);
    }


    /**
     * 针对于customers表的通用操作 Customer customer = queryForCustomers(sql,13);
     */
    public Customer queryForCustomers(String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2.预编译sql语句，返回PrepareStatement的实例
            ps = conn.prepareStatement(sql);

            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }

            //4.执行,并返回结果集
            rs = ps.executeQuery();

            //获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            //5.处理结果集
            if (rs.next()){
                Customer cust = new Customer();

                //处理结果集一个数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columValue = rs.getObject(i + 1);

                    //获取每个列的列名
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，赋值为columValue,通过反射
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust,columValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭连接
            JDBCUtils.closeResoure(conn,ps,rs);
        }
        return null;
    }

    @Test
    public void testQuery1(){

        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1.获取数据库的连接
            coon = JDBCUtils.getConnection();

            String sql = "select id,name,email,birth from customers where id = ?";

            //2.预编译sql语句，返回PrepareStatement的实例
            ps = coon.prepareStatement(sql);

            //3.填充占位符
            ps.setObject(1,1) ;

            //4.执行,并返回结果集
            resultSet = ps.executeQuery();
            //5.处理结果集
            if (resultSet.next()){//判断结果集的下一条是否有数据，如果有数据返回true，并指针下移，如果返回false，指针不会下移

                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                //方式一
    //            System.out.print(id);
    //            System.out.print(name);
    //            System.out.print(email);
    //            System.out.println(birth);

                //方式二
    //            Object[] data = new Object[]{id,name,email,birth};

                //方式三:将数据封装为一个对象(推荐)
                Customer customer = new Customer(id,name,email,birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        //6.关闭资源
        JDBCUtils.closeResoure(coon,ps,resultSet);
        }
    }
}