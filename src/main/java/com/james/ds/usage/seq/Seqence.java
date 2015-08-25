package com.james.ds.usage.seq;

import java.util.ArrayList;
import java.util.List;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;
import static com.james.ds.Utils.pn;

/**
 * 序列相关算法
 */
public class Seqence {

    /**
     * 寻找一组数中（正数和负数）最大子序列和
     * 例如： {1, -2, 3, 10, -4, 7, 2, -5} 最大子数组为{3, 10, -4, 7, 2} 和为18
     * 累加数组中每个数字， 记录当前最大序列的和，
     * 当累加到当前数字的和比当前数字还小时，放弃之前累加的子序列，由当前数字继续累加
     * 当累加的和比最大子数组和小时， 保存子数组（可能为最大子数组）
     * O（n）
     */
    public static void findMaxSumOfSubSeq(Integer[] arrs ){
        Integer currSum = 0;
        Integer maxSum = 0;
        List<Integer> subSeqs = new ArrayList<Integer>();
        List<Integer> subMaxSeqs = new ArrayList<Integer>();
        for (Integer num : arrs){
            currSum += num;
            //当累加到当前数字的和比当前数字还小时，放弃之前累加的子序列，由当前数字继续累加
            if(currSum < num){
                subSeqs.clear();
                maxSum = num;
                currSum = num;
            }
            subSeqs.add(num);
            if(currSum > maxSum) {
                maxSum = currSum;
                subMaxSeqs.clear();
                subMaxSeqs.addAll(subSeqs);
            }

        }
        pln("max sum : " + maxSum);
        plns(subMaxSeqs.toArray());
    }


    private static void findMaxSumOfSubSeqV1(Integer[] arrs ){
        Integer currSum = 0;
        Integer maxSum = 0;
        List<Integer> subSeqs = new ArrayList<Integer>();
        List<Integer> subMaxSeqs = new ArrayList<Integer>();
        for (Integer num : arrs){
            currSum += num;
            subSeqs.add(num);
            if(currSum > maxSum) {
                maxSum = currSum;
                subMaxSeqs.clear();
                subMaxSeqs.addAll(subSeqs);
            }
            //当累加到当前数字的和小于0 则重新开始累加
            else if(currSum < 0){
                currSum = 0;
                subSeqs.clear();
            }
        }
        pln("max sum : " + maxSum);
        plns(subMaxSeqs.toArray());
    }



    /**
     * 统计一个数字K在一组已排序数组中出现的次数
     * 考察2分查找的灵活应用
     * 先找到这个数第一次出现的位置 如果比较后 较大则只可能出现在数组后半段， 小的话则只可能出现在前半段
     * 再找到这个数最后出现的位置
     */
    public static Integer findNumberOfK(Integer[] arrs, Integer k){
        int lo = 0;
        int hi = arrs.length - 1;
        int num = 0;
        int mid = -1;
        while (hi >= lo){
            mid = ((hi - lo) >> 2) + lo;
            if(k > arrs[mid]) lo = mid + 1;
            else if(k < arrs[mid]) hi = mid - 1;
            else{
                num++;
                break;
            }
        }

        if(mid > -1){
            Integer first = mid - 1;
            Integer end  = mid + 1;
            while(first >= 0){
                if(arrs[first--].equals(k)){
                    num++;
                }
            }
            while(end < arrs.length){
                if(arrs[end++].equals(k)){
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * 输入一个整数n 求从1到n个整数十进制表示中1(countNum)出现的次数  例如12，从1到12中包含1的有1,10,11和12 一共5次
     * @param n
     * @return
     */
    public static Integer findNumOfBetween1AndN(Integer n, Integer countNum){
        Integer total_count = 0;
        for (int i = 1; i<= n; i++){
            int num = i;
            int count = 0;
            while (num > 0){
                if(num % 10 == countNum)
                    count++;
                num = num / 10;
            }
            total_count += count;
        }
        return total_count;
    }

    /**
     * 寻找丑数
     * @param index_count
     * @return
     */
    public static Integer findUglyNumberCount(Integer index_count){
        Integer[] ugly_arrs = new Integer[index_count];
        int found_count = 0;
        int index = 0;
        int number = 0;
        while (found_count < index_count){
            if(isUgly(++number)){
                found_count++;
                ugly_arrs[index++] = number;
            }
        }
        plns(ugly_arrs);
        return found_count;
    }

    private static Boolean isUgly(int num){
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }
    /**
     * 寻找丑数个数， 只包含因子2、3和5的数称为丑数（6,8是丑数） 所谓一个数m是另一个数n的因子 是指n能被m整除
     *
     * 丑数一定是之前丑数乘以2、3或者5的结果
     * 找到之前丑数相乘以后大之前的第一最小个数， 保持丑数数组的从小到大的顺序
     */
    public static Integer findUglyNumberCountV2(Integer index){
        Integer[] ugly_arrs = new Integer[index];
        ugly_arrs[0] = 1;
        Integer m2 = 0;
        Integer m3 = 0;
        Integer m5 = 0;
        int curr_index = 1;
        while (curr_index < index){
            Integer min = min(ugly_arrs[m2] * 2, ugly_arrs[m3] * 3, ugly_arrs[m5] * 5);
            ugly_arrs[curr_index] = min;
            while (ugly_arrs[m2] * 2 <= ugly_arrs[curr_index]) ++m2;
            while (ugly_arrs[m3] * 3 <= ugly_arrs[curr_index]) ++m3;
            while (ugly_arrs[m5] * 5 <= ugly_arrs[curr_index]) ++m5;
            ++curr_index;
        }
        plns(ugly_arrs);
        return curr_index;
    }

    private static Integer min(Integer n1, Integer n2, Integer n3){
        int min = n1 < n2 ? n1 : n2;
        min = min < n3 ? min : n3;
        return min;
    }

    /**
     * 数组中只出现过一次的数字
     * 一个数组内，除了两个数字外，其他数字都出现了2次
     * 如果只有一个：通过异或计算遍历数组，最终结果刚好是出现一次的数字,因为相同数字异或等于0
     * @param arrs
     * @return
     */
    public static List<Integer> findNumsAppearOnce(Integer[] arrs){
        List<Integer> onces = new ArrayList<Integer>();
        //TODO
        return onces;
    }

    /**
     * 输入一个递增排序的数组和一个数字s 在数组中查找两个数使得他们的和正好是s
     * 解法：
     * 两个指针，分别指向排序数组的头和尾
     * 如果当前相加之和大于数字s，则向后移动尾指针，小于则向前移动头指针
     * 1, 2, 3, 4, 11, 25, 25, 25, 29, 29, 29
     * ↑                                   ↑
     *
     * @return
     */
    public static Boolean findNumWithSumInSortArr(Integer[] sortArrs, Integer sum){
        Integer head = 0;
        Integer tail = sortArrs.length - 1;
        while (head < tail){
           Integer currSum = sortArrs[head] + sortArrs[tail];
           if(currSum.equals(sum)){
               return Boolean.TRUE;
           }
           if(currSum > sum)
               tail--;
            else{
               head++;
           }
        }
        return Boolean.FALSE;
    }

    /**
     * 输入一个正数s， 打印出所有和为s的连续正数列(至少含有2个数)
     * 如：输入15  1+2+3+4+5 = 4+5+6 = 7+8
     * 所以打印出3个连续序列
     * 解法：
     * 选择2个指针，small 和big 分别初始化为1,2
     * 如果 当前序列之和 小于正数s 则big增加1  如果大于正数s 则增加small 等于则打印序列并增加big
     * small和big 由于至少含有2个数，故最终都不得大于 (s+1)/2
     * @param sum
     */
    public static void findContinuousSeq(Integer sum){
        if(sum < 3) return;
        Integer small = 1;
        Integer big = 2;
        Integer currSum = small + big;
        Integer mid = (Integer)(sum + 1)/2;
        while (small < mid){
            if(currSum.equals(sum)){
                printSeq(small, big);
            }
            //移动small指针 在前一个序列基础上找操作之和的序列和
            while (currSum > sum && small < mid){
                currSum -= small; //去除一位最小的
                small++;
                if(currSum.equals(sum)){
                    printSeq(small, big);
                }
            }
            big++;
            currSum += big;
        }
    }

    //打印序列
    private static void printSeq(Integer small, Integer big){
        for (int i = small; i <= big; i++) {
            pn(i + " ");
        }
        pln();
    }

    /**
     * 斐波那契数列  递归版
     */
    public static int Fibonacci(int n) {
        if(n == 0) return 0;
        if(n <= 2) return 1;
        return Fibonacci(n - 2) + Fibonacci(n - 1);
    }

    /**
     * 斐波那契数列
     */
    public static int FibonacciV1(int n) {
        if(n == 0) return 0;
        if(n <= 2) return 1;
        int pre1 = 1;
        int pre2 = 1;
        int num = 2;
        int curr = pre2 +pre1;
        while (++num <= n){
            curr = pre2 +pre1;
            pre1 = pre2;
            pre2 = curr;
        }
        return curr;
    }

    public static void main(String[] args) {
        Integer[] arrs = {1, -2, 3, 10, -4, 7, 2, -5};
        findMaxSumOfSubSeq(arrs);
        findMaxSumOfSubSeqV1(arrs);

        Integer[] sort_arrs = {1, 2, 3, 4, 11, 25, 25, 25, 29, 29, 29};
        pln("Number Of K : " + findNumberOfK(sort_arrs, 25));

        pln("1 is appear count : " + findNumOfBetween1AndN(12, 1));

        pln("find ugly count : " + findUglyNumberCount(15));
        pln("find ugly count V2 : " + findUglyNumberCountV2(15));

        pln("find num with sum  : " + findNumWithSumInSortArr(sort_arrs, 36));

        pln("findContinuousSeq  : ");
        findContinuousSeq(15);

        Integer numOfFibonacci = 45;
        long begin = System.currentTimeMillis();
        pln("Fibonacci  : " + Fibonacci(numOfFibonacci));
        pln("Fibonacci cost time  : " + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        pln("Fibonacci v1 : " + FibonacciV1(numOfFibonacci));
        pln("Fibonacci V1 cost time  : " + (System.currentTimeMillis() - begin));
    }
}
