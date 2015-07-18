package com.james.ds.sort;

import static com.james.ds.Utils.plns;

/**
 * 桶排序 对一组限定范围的对象进行排序
 */
public class BucketSort {

	public static void sort(Integer arrs[], int max) {
		// 初始化桶（最大范围值大小的数组）
		Integer[] counts = new Integer[max];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 0;
		}
		// 记录每个元素排序后的位置
		Integer[] temps = new Integer[arrs.length];
		for (int i = 0; i < arrs.length; i++) {
			int index = arrs[i];
			counts[index]++;
		}
		// 临时数组记录元素在原始数组中排序后位置
		int index = 0;
		for (int i = 0; i < counts.length; i++) {
			while(counts[i] > 0 ){
				arrs[index++] = i;
				counts[i] = --counts[i];
			}
			
		}
		// 放回原始数组中
	}

	public static void main(String[] args) {
		// 一组年龄排序
		Integer[] arrs = { 23, 11, 87, 1, 23, 78, 12, 93, 50, 2, 11, 2 };
		sort(arrs, 99);
		plns(arrs);
	}

}
