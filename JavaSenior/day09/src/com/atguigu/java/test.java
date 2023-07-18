package com.atguigu.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author steven
 * @create 2023-05-24 16:28
 */
public class test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        list.add("456");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println();
        for (String s : list){
            System.out.println(s);
        }
    }
}
