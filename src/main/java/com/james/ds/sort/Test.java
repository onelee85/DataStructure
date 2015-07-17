package com.james.ds.sort;

import com.james.ds.tree.*;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;

public class Test {

	public static void main(String[] args) {
        Integer[] arrs = {23,11,445,1,23,78,12,93,333,2,11,2};
        QuickSort qs = new QuickSort();
        qs.sort(arrs);
        plns(arrs);
	}
}
