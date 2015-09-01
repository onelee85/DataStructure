package com.james.ds.usage.seq;

import static com.james.ds.Utils.*;
import static com.james.ds.Utils.pln;

/**
 * 数组相关
 */
public class Array {

    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * @param array
     * @param target
     * @return
     */
    public static boolean FindNumInMatrix(int [][] array,int target) {
        if(array == null || array.length == 0) return Boolean.FALSE;
        //先从数组右上角开始比较
        int i = 0;
        int j = array[0].length - 1;
        while (i < array.length && j >= 0){
            //如果比右对角数字小 则向左移动
            if(target < array[i][j]) j--;
            //如果比右上角大则像下移动
            else if(target > array[i][j]) i++;
            else  return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        int [][] arrays = { {1, 2, 8, 9},
                            {2, 4, 9, 12},
                            {4, 7, 10,13},
                            {6, 8, 11,15}};
        pln("FindNumInMatrix : "+ FindNumInMatrix(arrays, 100));
    }
}
