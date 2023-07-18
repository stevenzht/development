package com.atguigu3.dao.com.atguigu3.dao.junit;

import com.atguigu4.utils.JDBCUtils;
import com.atguigu2.bean.Customer;
import com.atguigu3.dao.CustomerDAOImp1;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author steven
 * @create 2023-06-13 15:26
 */
public class CustomerDAOImp1Test {

    private CustomerDAOImp1 dao = new CustomerDAOImp1();

    @Test
    public void testInsert(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = new Customer(1, "周周", "zhouzhou@com", new Date(123341235L));
            dao.insert(conn,cust);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    @Test
    public void testDeleteById(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.deleteById(conn,13);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    @Test
    public void testUpdateConnectionCustomer(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            Customer cust = new Customer(18,"贝多芬","beiduofen@com",new Date(123456798L));
            dao.updateById(conn,cust);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    @Test
    public void testGetCustomerById(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection3();

            Customer cust = dao.getCustomerById(conn, 19);
            System.out.println(cust);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    @Test
    public void testGetAll(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Customer> list = dao.getAll(conn);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    @Test
    public void testGetCount(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            Long count = dao.getCount(conn);

            System.out.println("表中的记录数为：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

    @Test
    public void testGetMaxBirth(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            Date maxBirth = dao.getMaxBirth(conn);

            System.out.println("最大的生日为：" + maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,null);
        }
    }

}
