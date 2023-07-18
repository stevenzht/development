package com.atguigu.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁 ----JDK5.0新增
 *
 * 1.面试题：synchronized 与 Lock的异同？
 *  相同：二者都可以解决线程安全问题
 *  不同：synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
 *      Lock需要手动的启动同步(Lock())，同时结束同步也需要手动的实现(unlock())
 *
 *      隐式锁/显式锁
 *      Lock只有代码块锁，synchronized有代码块锁和方法锁
 *      使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）
 *
 * 2.优先使用顺序
 *  Lock--同步代码块--同步方法
 *
 *  面试题：如何解决线程安全问题？有几种方式
 *
 * @author steven
 * @create 2023-05-08 20:28
 */

class Window implements Runnable{

    private int ticket = 100;

    //实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock(true);


    @Override
    public void run() {
        while (true){
            try {

                //2.调用锁定方法lock()
                lock.lock();

                if (ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
                //3.调用解锁方法：unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {

    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
