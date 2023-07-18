package com.atguigu3.preparedstatement.crud;

import com.atguigu3.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 演示使用PreparedStatement替换Statement，解决sql注入问题
 * @author steven
 * @create 2023-06-11 17:34
 *
 * 除了解决Statement的拼串、sql注入问题之外，PreparedStatement还有哪些好处
 * 1.PreparedStatement可以操作Blob的数据，而Statement做不到
 * 2.PreparedStatement可以实现更高效的批量操作
 */
public class PreparedStatementTest {

//    @Test
//    public void testLogin(){
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("请输入用户名：");
//        String user = scanner.nextLine();
//        System.out.println("请输入密码：");
//        String password = scanner.nextLine();
//        String sql = "select user,password from user_table where user = ";
//        User re
//    }

    /**
     * 针对于不同的表的通用的查询操作，返回表中的一条记录
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> clazz,String sql,Object...args){
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
                T t = clazz.newInstance();

                //处理结果集一个数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columValue = rs.getObject(i + 1);

                    //获取每个列的列名
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，赋值为columValue,通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭连接
            JDBCUtils.closeResoure(conn,ps,rs);
        }
        return null;
    }
}
