package com.james.ds.usage.tree;

import com.james.ds.tree.Heap;

import java.util.ArrayList;
import java.util.Collections;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;

/**
 *
 */
public class MinKNumber {

    /**
     * 输入N个整数，找到其中最小的k个数 或者最大的K个数
     * 可利用最大堆  和最小堆
     * O(nlogk)
     * @param arrs
     * @param k
     */
    private static void findMinKNumber(Integer[] arrs, int k){
        //创建最大堆
        Heap<Integer> heap = new Heap<Integer>(k);
        for (int i = 0; i < arrs.length; i++) {
            if(i < k)
                heap.insert(arrs[i]);
            else if(heap.getMax() > arrs[i]){
                heap.delMax();
                heap.insert(arrs[i]);
            }
        }
        heap.print();
    }

    /**
     * 利用2分法进行查找
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(input == null || input.length == 0 || k <= 0 || k > input.length) return result;
        int lo = 0;
        int hi = input.length - 1;
        int indexK = getPartion(input, lo, hi);
        pln("indexK = " + indexK);
        while (indexK != k - 1){

            if(indexK > k - 1)
                hi = indexK - 1;
            else
                lo = indexK + 1;
            indexK = getPartion(input, lo, hi);
            pln("indexK = " + indexK);
        }
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        Collections.sort(result);
        return result;
    }

    private static int getPartion(int arr[], int lo, int hi) {
        int piovt = arr[lo];
        while (hi != lo) {
            while (hi > lo && arr[hi] >= piovt)
                hi--;
            arr[lo] = arr[hi];
            while (hi > lo && arr[lo] <= piovt)
                lo++;
            arr[hi] = arr[lo];
        }
        arr[hi] = piovt;
        return hi;
    }

    public static void main(String[] args) {
        Integer[] arrs = {4,10,1,22,7,3,9,6,5,13};
        findMinKNumber(arrs, 5);
        int[] arrs1 = {4,10,1,22,7,3,9,6,5,13};
        plns(GetLeastNumbers_Solution(arrs1, 4));
    }
}
