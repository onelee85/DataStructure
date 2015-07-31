package com.james.ds.usage.tree;

import com.james.ds.tree.Heap;

/**
 * 输入N个整数，找到其中最小的k个数 或者最大的K个数
 * 可利用最大堆  和最小堆
 * O(nlogk)
 */
public class MinKNumber {


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

    public static void main(String[] args) {
        Integer[] arrs = {4,10,1,22,7,3,9,6,5,13};
        findMinKNumber(arrs, 5);
    }
}
