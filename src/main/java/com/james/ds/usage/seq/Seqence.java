package com.james.ds.usage.seq;

import java.util.ArrayList;
import java.util.List;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;

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
     * 先找到这个数第一次出现的位置 如果比较后 比较大则只可能出现在数组后半段， 小的话则只可能出现在前半段
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

    public static void main(String[] args) {
        Integer[] arrs = {1, -2, 3, 10, -4, 7, 2, -5};
        Integer[] arrs1 = {1, -2, 3, 10, -4, -7, -2, -5};
        Integer[] arrs2 = {11, -2, 3, 10, -4, 7, -2, -5};
        findMaxSumOfSubSeq(arrs);
        findMaxSumOfSubSeqV1(arrs);
        findMaxSumOfSubSeq(arrs1);
        findMaxSumOfSubSeqV1(arrs1);
        findMaxSumOfSubSeq(arrs2);
        findMaxSumOfSubSeqV1(arrs2);

        Integer[] sort_arrs = {1, 2, 3, 4, 11, 25, 25, 25, 29, 29, 29};
        pln("Number Of K : " + findNumberOfK(sort_arrs, 25));
    }
}
