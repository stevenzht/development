package atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式
 * 存在线程安全的问题，待解决
 *
 *
 * @author steven
 * @create 2023-05-03 19:02
 */

class window1 implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        while (true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}


public class WindowTest1 {
    public static void main(String[] args) {
        window w = new window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
