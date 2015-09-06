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

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减序列的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * 解:
     * 由于是个非递减序列，所以必定是2端递增数组 {3,4,5,   1,2}
     * 两个指针分别指向头尾，
     * 比较头指针元素和中间元素， 如果小于则移动头指针到中间元素位置
     * 比较尾指针元素和中间元素，如果大于则移动尾指针到中间元素
     * 如果只剩余2个元素，尾指针指向的则是最小元素
     * @param array
     * @return
     */
    public static int minNumberInRotateArray(int [] array) {
        int starIndex = 0;
        int endIndex = array.length - 1;
        int midIndex = starIndex;
        while (array[starIndex] >= array[endIndex]){
            if(endIndex - starIndex == 1){
                midIndex = endIndex;
                break;
            }
            midIndex = (starIndex + endIndex)/2;
            if(array[starIndex] == array[endIndex] &&
                    array[starIndex] == array[midIndex] ){
                return minInOrder(array, starIndex, endIndex);
            }
            if(array[starIndex] < array[midIndex])
                starIndex = midIndex;
            else if(array[endIndex] >= array[midIndex])
                endIndex = midIndex;
        }
        return array[midIndex];
    }

    private static int minInOrder(int [] array, int start, int end){
        int result = array[start];
        for (int i = start+1; i <= end; i++) {
            if(array[i] < result){
                result = array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [][] matrix = { {1, 2, 8, 9},
                            {2, 4, 9, 12},
                            {4, 7, 10,13},
                            {6, 8, 11,15}};
        pln("FindNumInMatrix : "+ FindNumInMatrix(matrix, 11));

        int [] arrays = {3,4,5,1,2};
        pln("minNumberInRotateArray [3,4,5,1,2]: "+ minNumberInRotateArray(arrays));
    }
}
