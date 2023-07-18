package com.atguigu.java;

/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承于java.lang.Enum类
 *
 *
 * @author steven
 * @create 2023-05-14 9:36
 */
public class SeasonTest1 {

    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        //toString():返回枚举类对象的名称
        System.out.println(summer.toString());

        //System.out.println(Season1.class.getSuperclass());
        System.out.println("**********");
        //values():返回所有的枚举类对象构成的数组
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
            values[i].show();
        }

        System.out.println("********");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }

        //valueOf(String objName):返回枚举类中对象名是objName的对象
        //如果没有objName的枚举类的对象，则抛异常：
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);
        winter.show();

    }
}

interface Info{
    void show();
}

//使用enum关键字定义枚举类
enum  Season1 implements Info{

    //1.提供当前枚举类的对象，多个对象之间用,隔开，末尾对象;结束
    SPRING("春天","春暖花开") {
        @Override
        public void show() {
            System.out.println("春天在哪里");
        }
    },
    SUMMER("夏天","热") {
        @Override
        public void show() {
            System.out.println("夏天在哪里");
        }
    },
    AUTUMN("秋天","果子") {
        @Override
        public void show() {
            System.out.println("秋天在哪里");
        }
    },
    WINTER("冬天","雪") {
        @Override
        public void show() {
            System.out.println("踩雪");
        }
    };

    //1.声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器

    private Season1(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //4.其他述求1：获取枚举类对象的属性

    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    //4.其他述求2：提供toString();
//
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }
}
