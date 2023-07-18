package com.atguigu.test;



/**
 * @author steven
 * @create 2023-06-28 14:29
 */
public class OrderService {
    public void createOrder(){
        String name = Thread.currentThread().getName();
        System.out.println("OrderService 当前线程[" + name + "]中保存的数据是：" +
                ThreadLocalTest.threadLocal.get());
        new OrderDao().saveOrder();
    }
}
