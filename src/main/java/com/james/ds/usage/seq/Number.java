package com.james.ds.usage.seq;

import java.util.Arrays;

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

    /**
     * 扑克牌顺子
     * 王可以看成任何数字,并且A看作1,2~10为数字本身 J为11,Q为12,K为13
     * 为了方便起见,你可以认为大小王是0 0可以作为任意数字
     *
     * 解法:
     * 1.先把数组排序
     * 2.统计数组中0的个数
     * 3.然后查看数组之间是否为连续的，统计空缺的总数小于或者等于0的个数
     * 4.如果出现对子则不可能是顺子
     * @param numbers
     * @return
     */
    public static boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length < 1) return Boolean.FALSE;
        //数组排序
        Arrays.sort(numbers);
        int zeroCount = 0;
        int missCount = 0;
        for (int i = 0, j = i+1; j < numbers.length ; i++,j++) {
            //统计数组中0的个数
            if(numbers[i] == 0){
                zeroCount++;
                continue;
            }
            //如果出现对子则不可能是顺子
            if(numbers[i] == numbers[j]) return Boolean.FALSE;
            //数组之间是否为连续的，统计空缺的总数
            if(numbers[j] - numbers[i] > 1) missCount += (numbers[j] - numbers[i]) - 1;
        }
        if(missCount <= zeroCount) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * 孩子们的游戏(圆圈中最后剩下的数)
     *
     * @param n
     * @param m
     * @return
     */
    public static int LastRemaining_Solution(int n, int m) {
        if(n < 1 || m < 1) return -1;
        int num = 0;
        int index = 2;
        while(index <= n){
            num = (num + m) % index++;
        }
        return num;
    }

    /**
     * 求1+2+3+...+n，
     * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * 利用条件运算短路的原理
     * @param n
     * @return
     */
    public static int Sum_Solution(int n) {
        int result = 0;
        Boolean isFinish = (n > 0) && (result = n + Sum_Solution(n - 1)) > 0;
        return result;
    }

    /**
     * 一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     * 1.通过转换为2进制进行运算
     * 2.对2个数异或运算
     * 3.进位 进行与运算  然后左位移1位， 如果大于0 证明有进位
     * @param num1
     * @param num2
     * @return
     */
    public static int add(int num1,int num2) {
        int sumNum = 0;
        int and = 0;
        do{
            sumNum = num1 ^ num2;
            and = (num1 & num2) << 1;
            num1 = sumNum;
            num2 = and;
        }while (num2 != 0);
        return sumNum;
    }
    public static void main(String[] args) {
        pln("NumberOf1 : "+ NumberOf1(-1));

        int[] numbers = {0, 1, 3, 4, 5};
        pln("poker  isContinuous: "+ isContinuous(numbers));

        pln("LastRemaining_Solution: "+ LastRemaining_Solution(5, 3));

        pln("Sum_Solution: "+ Sum_Solution(5));


        pln("add: "+ add(-5, -17));


    }
}
