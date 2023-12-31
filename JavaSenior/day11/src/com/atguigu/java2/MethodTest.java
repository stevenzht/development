package com.atguigu.java2;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author steven
 * @create 2023-06-04 8:51
 */
public class MethodTest {

    @Test
    public void test1(){

        Class clazz = Person.class;

        //getMethods()：获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods){
            System.out.println(m);
        }

        System.out.println();

        //getDeclaredMethods()：获取当前运行时类中声明的所有方法（不包含父类中声明的方法）
        Method[] declareMethods = clazz.getDeclaredMethods();
        for (Method m : declareMethods){
            System.out.println(m);
        }
    }

    /*
    @Xxx
    权限修饰符 返回值类型 方法名(参数类型1 形参名1,...)throws XxxException
     */
    @Test
    public void test2(){
        Class clazz = Person.class;
        Method[] declareMethods = clazz.getDeclaredMethods();
        for (Method m : declareMethods){
            //1.获取方法声明的注解
            Annotation[] annotation = m.getAnnotations();
            for (Annotation a : annotation){
                System.out.println(a);
            }

            //2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            //3.返回值类型
            System.out.print(m.getReturnType().getName() + "\t");

            //4.方法名
            System.out.print(m.getName() + "\t");
            System.out.print("(");

            //5.形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)){
                for (int i = 0;i < parameterTypes.length;i++){

                    if (i == parameterTypes.length - 1){
                        System.out.print(parameterTypes[i].getName() + "args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + "args_" + i + ",");
                }
            }
            System.out.print(")");

            //6.抛出的异常
            Class[] excpetionTypes = m.getExceptionTypes();
            if (excpetionTypes.length > 0){
                System.out.print("throws ");
                for (int i = 0; i < excpetionTypes.length; i++) {
                    if (i == excpetionTypes.length - 1){
                        System.out.print(excpetionTypes[i].getName());
                        break;
                    }
                    System.out.print(excpetionTypes[i].getName() + ",");
                }
            }

            System.out.println();
        }

    }
}
