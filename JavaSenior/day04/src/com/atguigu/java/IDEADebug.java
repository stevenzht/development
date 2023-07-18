package com.atguigu.java;

import org.junit.Test;

/**
 * @author steven
 * @create 2023-05-11 11:42
 */
public class IDEADebug {

    @Test
    public void testStringBuffer(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);//

        System.out.println(sb.length());//4

        System.out.println(sb);//"null"

        StringBuffer sb1 = new StringBuffer(str);
        System.out.println(sb1);

    }
}
