package com.atguigu.com.atguigu.exer;

import java.util.Objects;

/**
 * @author steven
 * @create 2023-05-18 16:58
 */
public class Person implements Comparable{
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Person() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person){
            Person p1 = (Person)o;
            return this.name.compareTo(p1.name);
        }else {
            throw new RuntimeException("输入数据类型不正确");
        }
    }
}
