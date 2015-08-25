package com.james.ds.usage.seq;

import static com.james.ds.Utils.*;
import static com.james.ds.Utils.pln;
/**
 * @author: jiao.li
 * Date: 2015/8/25 16:10
 */
public class Number {

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 解法1：
     * 先把输入数字和1做 与 运算 （相当于低位开始）
     * 再把1 左位移1位得到2 再与输入数字n做与 运算 比较第二位
     *
     * 解法2：
     *
     * @param n
     * @return
     */
    public static int NumberOf1(int n) {
        int count = 0;
        while (n > 0){
            count++;
            n = (n - 1) & n;
        }
        return count;
    }



    public static void main(String[] args) {
        pln("NumberOf1 : "+ NumberOf1(-1));
    }
}
