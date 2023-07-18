package com.atguiguq.transaction;

import com.atguigu1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author steven
 * @create 2023-06-12 10:51
 */
public class ConnectionTest {

    @Test
    public void testGetConnection() throws Exception {

        Connection conn = JDBCUtils.getConnection();


    }
}
