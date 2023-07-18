package com.atguigu.exer;

/**
 * @author steven
 * @create 2023-05-18 14:43
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }
    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public MyDate getBirthday() {
        return birthday;
    }
    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    //指明泛型时的写法
    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }

    //没有指明泛型时的写法
//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof Employee){
//            Employee e = (Employee)o;
//            return this.name.compareTo(e.name);
//        }else{
//            throw new RuntimeException("传入的数据类型不一致");
//        }
//    }
}
