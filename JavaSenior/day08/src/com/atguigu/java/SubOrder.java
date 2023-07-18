package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steven
 * @create 2023-05-22 16:33
 */
public class SubOrder extends Order<Integer>{//SubOrder:不是泛型类

    public static  <E> List<E> copyFroArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;
    }
}
