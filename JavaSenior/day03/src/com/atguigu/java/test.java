package com.atguigu.java;

/**
 * @author steven
 * @create 2023-05-11 9:48
 */
public class test {

    public static void main(String[] args) {

        int i = 1;

        while (true){
            System.out.println("周周想冉冉第" + i++ + "次");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
