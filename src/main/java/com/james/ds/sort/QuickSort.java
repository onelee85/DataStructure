package com.james.ds.sort;

/**
 * 快速排序
 */
public class QuickSort {

    public void sort(Integer arr[]){
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Integer arr[], int lo, int hi) {
        if (hi - lo < 2) return;
        int mid = getPartion(arr, lo, hi);
        quickSort(arr, lo, mid - 1);
        quickSort(arr, mid + 1, hi);
    }

    private int getPartion(Integer arr[], int lo, int hi){
        int piovt = arr[lo];
        while(hi != lo){
            while(hi > lo && arr[hi] >= piovt) hi--;
            arr[lo] = arr[hi];
            while(hi > lo && arr[lo] <= piovt) lo++;
            arr[hi] = arr[lo];
        }
        arr[hi] = piovt;
        return hi;
    }


}
