package com.atguigu4;

import com.atguigu3.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 课后练习2
 * @author steven
 * @create 2023-06-11 18:40
 */
public class Exer2Test {

    //问题1：向examstudent表中添加一条记录
//    @Test
//    public void testinsert(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println();
//
//    }


    //通用的增删改操作
    public int update(String sql,Object ...args){//sql中占位符的个数与可变形参的长度相同
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2.预编译sql语句，返回PrepareStatement的实例
            ps = conn.prepareStatement(sql);

            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);//小心参数声明错误！
            }

            //4.执行
            /*
            ps.execute():
            如果执行的是查询操作，有返回结果，则此方法返回true
            如果执行的是增、删、改操作，没有返回结果，则此方法返回false
             */
            //方式一
//            return ps.execute();
            //方式二
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResoure(conn,ps);
        }
        return 0;
    }
}
