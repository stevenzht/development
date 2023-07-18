package com.atguigu.java;

/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 *
 * 关于同步方法的总结：
 * 1.同步方法仍然涉及到同步监视器，只是不需要我们显式的声明
 * 2.非静态的同步方法，同步监视器是：this
 *  静态的同步方法，同步监视器是：当前类本身
 *
 *
 * @author steven
 * @create 2023-05-07 21:51
 */
class window4 extends Thread{

    private static int ticket = 100;

    @Override
    public void run() {

        while (true){

        }
    }

    private static synchronized void show(){//同步监视器:Window4.class
    //private synchronized void show(){//同步监视器：t1,t2,t3,此种解决方式是错误的
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
            ticket--;
        }
    }
}

public class WindowTest4 {

    public static void main(String[] args) {

        window4 w1 = new window4();
        window4 w2 = new window4();
        window4 w3 = new window4();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }
}
