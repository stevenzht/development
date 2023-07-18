package com.atguigu.java;

import com.atguigu.com.atguigu.exer.Person;
import org.junit.Test;

import java.util.*;

/**
 * @author steven
 * @create 2023-05-20 16:05
 */
public class TreeMapTest {

    //向TreeMap中添加key-value，要求key必须是由同一个类创建的对象
    //因为要按照key进行排序：自然排序、定制排序
    @Test
    public void test1(){
        TreeMap map = new TreeMap();

        Person p1 = new Person(1,"Jey");
        Person p2 = new Person(2,"Tom");
        Person p3 = new Person(3,"Steven");

        map.put(p1,98);
        map.put(p2,89);
        map.put(p3,76);

        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry)obj;
            System.out.println(entry.getKey() + "----" + entry.getValue());
        }
    }

    //定制排序
    @Test
    public void test2(){
        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person)o1;
                    Person p2 = (Person)o2;
                    return Integer.compare(p1.getId(),p2.getId());
                }
                throw new RuntimeException("输入的数据类型不正确");
            }
        });

        Person p1 = new Person(1,"Jey");
        Person p2 = new Person(2,"Tom");
        Person p3 = new Person(3,"Steven");

        map.put(p1,98);
        map.put(p2,89);
        map.put(p3,76);

        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry)obj;
            System.out.println(entry.getKey() + "----" + entry.getValue());
        }
    }
}
