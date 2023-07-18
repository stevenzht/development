package com.atguigu5.blob;

import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 使用PreparedStatement实现批量数据的操作
 *
 * update、delete本身就具有批量操作的效果
 * 此时的批量操作，主要指的是批量插入。使用PreparedStatement如何实现更高效的批量插入
 *
 * 方式一：使用Statement
 *
 * @author steven
 * @create 2023-06-12 9:33
 */
public class InsertTest {
    //批量插入的方式二：使用PrepareStatement
    @Test
    public void testInsert1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < 20000; i++) {
                ps.setObject(1,"name_" + i);
                ps.execute();
            }

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为：" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,ps);
        }
    }

    /**
     * 批量插入的方式三：
     * 1.addBatch()、executeBatch()、clearBatch()
     * 2.mysql服务器默认是关闭批处理的，我们需要通过一个参数，让mysql开启批处理的支持。
     * 		 ?rewriteBatchedStatements=true 写在配置文件的url后面
     * 3.使用更新的mysql驱动
     */
    @Test
    public void testInsert2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);

            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1,"name_" + i);

                //1.“攒”sql
                ps.addBatch();

                if(i % 500 == 0){
                    //2.执行batch
                    ps.executeBatch();

                    //3.清空batch
                    ps.clearBatch();
                }
            }

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为：" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,ps);
        }
    }

    //批量操作的方式四：设置连接不允许自动提交数据
    @Test
    public void testInsert3() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();

            //设置不允许自动提交数据
            conn.setAutoCommit(false);

            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);

            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1,"name_" + i);

                //1.“攒”sql
                ps.addBatch();

                if(i % 500 == 0){
                    //2.执行batch
                    ps.executeBatch();

                    //3.清空batch
                    ps.clearBatch();
                }
            }

            //提交数据
            conn.commit();

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为：" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResoure(conn,ps);
        }
    }
}
