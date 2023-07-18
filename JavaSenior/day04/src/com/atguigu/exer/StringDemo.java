package com.atguigu.exer;

/**
 * @author steven
 * @create 2023-05-11 9:20
 */
public class StringDemo {
    private static int count = 0;

    public static int getCount(String mainStr,String subStr){

        if(mainStr.length() >= subStr.length()){
            StringBuilder str = new StringBuilder(mainStr);

            for(int i = 0;i < mainStr.length()-subStr.length();i++){

                if(subStr.equals(str.substring(0 + i,subStr.length() + i))){
                    count++;
                }
            }
            return count;
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int count = getCount("abcabcabcabababc", "ab");

        System.out.println(count);
    }


}
