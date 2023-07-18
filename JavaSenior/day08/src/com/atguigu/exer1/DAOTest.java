package com.atguigu.exer1;

import java.util.List;

/**
 * @author steven
 * @create 2023-05-23 15:15
 */
public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();

        dao.sava("1001",new User(1001,34,"周杰伦"));
        dao.sava("1002",new User(1002,20,"昆凌"));
        dao.sava("1003",new User(1003,30,"蔡依林"));

        dao.update("1003",new User(1003,30,"方文山"));
        dao.delete("1002");

        List<User> list = dao.list();
        list.forEach(System.out::println);
    }
}
