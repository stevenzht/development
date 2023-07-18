package com.atguigu.java;

/**
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器
 *
 *
 * @author steven
 * @create 2023-05-03 18:38
 */

class window2 extends Thread{

    private static int ticket = 100;

    private static Object obj = new Object();

    @Override
    public void run() {

        while (true){
            //正确的
//            synchronized(obj) {
            synchronized (window2.class){//这个位置需要放对象，类也是对象;Window2.class只会加载一次
                //Class clazz = Window2.class
                //错误的，this代表着t1,t2,t3三个对象
            //synchronized (this){
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest2 {

    public static void main(String[] args) {

        window2 w1 = new window2();
        window2 w2 = new window2();
        window2 w3 = new window2();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }
}