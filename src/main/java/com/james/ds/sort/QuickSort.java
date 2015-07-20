package com.james.ds.sort;

import java.util.Random;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;

/**
 * 快速排序
 */
public class QuickSort {

    private Random random = new Random();

	public void sort(Integer arr[]) {
		quickSort(arr, 0, arr.length - 1);
	}

	private void quickSort(Integer arr[], int lo, int hi) {
		if (hi - lo < 1)
			return;
		int mid = getPartionRandom(arr, lo, hi);
		quickSort(arr, lo, mid - 1);
		quickSort(arr, mid + 1, hi);
	}

	private int getPartionRandom(Integer arr[], int lo, int hi) {
		int index = random.nextInt(hi);
        index = index < lo ? lo : index;
        int piovt = arr[index];
        int tmp = arr[lo];
        arr[lo]= arr[index];
        arr[index] = tmp;
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

    private int getPartion(Integer arr[], int lo, int hi) {
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
		Integer[] arrs = { 23, 11, 445, 1, 23, 78, 12, 93, 333, 2, 11, 2 };
		QuickSort qs = new QuickSort();
		qs.sort(arrs);
		plns(arrs);
	}
}
