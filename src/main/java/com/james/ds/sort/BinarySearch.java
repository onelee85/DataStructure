package com.james.ds.sort;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;

/**
 * 二分查找
 */
public class BinarySearch {

    public Integer search(Integer[] arrs, Integer val){
        return binarySearchV1(arrs, val, 0, arrs.length-1);
    }

    /**
     * 递归
     */
    private Integer binarySearch(Integer[] arrs, Integer val, Integer lo, Integer hi) {
        if(lo > hi) return -1;
        Integer mid = (hi + lo) / 2;
        if (val > arrs[mid]) return binarySearch(arrs, val, mid + 1, hi);
        else if (val < arrs[mid]) return binarySearch(arrs, val, lo, mid - 1);
        return mid;
    }

    /**
     * 非递归
     */
    private Integer binarySearchV1(Integer[] arrs, Integer val, Integer lo, Integer hi) {
        while(hi >= lo){
            Integer mid = (hi + lo) / 2;
            if (val > arrs[mid]) lo = mid + 1;
            else if (val < arrs[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        // 一组年龄排序
        Integer[] arrs = { 23, 11, 445, 1, 23, 78, 12, 93, 333, 2, 11, 2 };
        QuickSort qs = new QuickSort();
        qs.sort(arrs);
        plns(arrs);
        pln();
        BinarySearch bs = new BinarySearch();
        pln(bs.search(arrs, 445));
        pln(bs.search(arrs, 333));
        pln(bs.search(arrs, 1));
        pln(bs.search(arrs, 12));
    }
}
