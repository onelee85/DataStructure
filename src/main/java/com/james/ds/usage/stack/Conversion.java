package com.james.ds.usage.stack;

import com.james.ds.list.Stack;

/**
 * 1.逆序输出
 *
 * 进制转换
 */
public class Conversion {

    static char[] digits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    //进制转换
    public static void convert(Integer num, Integer converToNum){
        Stack<String> s = new Stack<String>();
        while (num > 0){
            s.push(digits[num % converToNum]+"");
            num = num/converToNum;
        }
        String c = null;
        while((c = s.popTop()) != null){
            System.out.print(c);
        }
    }

    public static void main(String[] args) {
        convert(111111, 16);
    }
}
