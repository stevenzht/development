package com.atguigu.java1;

import java.io.Serializable;

/**
 * @author steven
 * @create 2023-06-03 20:18
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    private void eat(){
        System.out.println("生物吃东西");
    }
}
