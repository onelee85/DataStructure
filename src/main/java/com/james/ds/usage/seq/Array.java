package com.james.ds.usage.seq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
    public static int minNumberInRotateArray(Integer [] array) {
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

    private static int minInOrder(Integer [] array, int start, int end){
        int result = array[start];
        for (int i = start+1; i <= end; i++) {
            if(array[i] < result){
                result = array[i];
            }
        }
        return result;
    }

    /**
     *  3给定一个无序数组arr，求出需要排序的最短子数组长度。
         例如：
         arr = [1，5，3，4，2，6，7]
         返回4，因为只有[5，3，4，2]需要排序。
     * @return
     */
    public static void findNeedSortSubArray(Integer [] array){
        if(array == null || array.length == 0) return;
        int max = array[0];
        int rightIndex = array.length - 1;
        int min = array[array.length - 1];
        int leftIndex = 0;

        for (int i = 0; i < array.length; i++) {
            //如果当前值大于max则替换当前值为max
            if(array[i] > max)
                max = array[i];
            else //如果当前数值比当前最大max的值要小，则它应该在max的左边， 记录当前的位置
                rightIndex = i;
        }
        for (int i = array.length-1; i >= 0; i--) {
            if(array[i] < min)
                min = array[i];
            else
                leftIndex = i;
        }
        for (int i = leftIndex; i <= rightIndex ; i++) {
            pn(array[i] +",");
        }
        pln();
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分， 所有的偶数位于位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * @param array
     */
    public static void reOrderArray(int [] array) {
        if(array == null || array.length <= 1) return;
        //记录偶数和奇数的个数
        int evenCount = 0;
        for (int i = 0; i < array.length; i++) {
            if(isEven(array[i])) evenCount++;
        }
        if(evenCount == 0 || array.length == evenCount) return;
        int oddIndex = 0;//奇数数组下表
        int evenIndex = array.length - evenCount; //偶数起始下标
        int [] arr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if(isEven(array[i]))
                arr[evenIndex++] = array[i];
            else
                arr[oddIndex++] = array[i];
        }
        System.arraycopy(arr, 0, array, 0, array.length);
    }

    private static Boolean isEven(int num){
        return (num & 1) == 0;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(matrix == null) return list;
        //2对角点[0, 0][3, 3]组成一个圈
        int startX = 0;
        int startY = 0;
        int endX = matrix.length-1;
        int endY = matrix[0].length-1;
        //打印完一圈 缩减对角点一圈
        while (startX <= endX && startY <= endY){
            printEdge(list, matrix, startX++, startY++, endX--, endY--);
        }
        return list;
    }

    /**
     * 打印边
     */
    private static void printEdge(ArrayList<Integer> list , int [][] matrix, int startX, int startY, int endX, int endY){
        //矩阵只有一行
        if(startX == endX){
            for (int i = startX; i <= endY; i++) {
                list.add(matrix[startX][i]);
            }
        }
        //矩阵只有一列
        else if(startY == endY){
            for (int i = startY; i <= endX; i++) {
                list.add(matrix[i][endY]);
            }
        }else{//一般情况
            int currX = startX;
            int currY = startY;
            //左至右打印行
            while(currX != endY){
                list.add(matrix[startX][currX]);
                currX++;
            }
            //上到下打印列
            while(currY != endX){
                list.add(matrix[currY][endY]);
                currY++;
            }
            //右至左打印行
            while(currX != startX){
                list.add(matrix[endX][currX]);
                currX--;
            }
            //下至上打印列
            while(currY != startY){

                list.add(matrix[currY][startY]);
                currY--;
            }
        }

    }

    public static void main(String[] args) {
        int [][] matrix = { {1, 2, 8, 9},
                            {2, 4, 9, 12},
                            {4, 7, 10,13},
                            {6, 8, 11,15}};
        pln("FindNumInMatrix : "+ FindNumInMatrix(matrix, 11));

        Integer [] arrays = {8, 4, 6, 7};
        pln("minNumberInRotateArray [3,4,5,1,2]: "+ minNumberInRotateArray(arrays));

        Integer [] arrs = {1,2,3,4,6,5,7};
        pln("findNeedSortSubArray [1,5,3,4,2,6,7]: ");
        findNeedSortSubArray(arrs);

        int [] needReOrders = {1,1, 1};
        reOrderArray(needReOrders);
        for (int i = 0; i < needReOrders.length; i++) {
            pn(needReOrders[i] + " ");
        }
        pln();
        int [][] matrix1 = {{1, 2, 8, 9}};
        int [][] matrix2 = {{1},{2},{4},{6}};
        int [][] matrix3 = {{1}};
        int [][] matrix4 = { {1, 2, 8, 9},
                             {2, 4, 9, 12},
                             {4, 7, 10,13}};
        int [][] matrix5 = { {1, 2, 8},
                             {2, 4, 9},
                             {4, 7, 10},
                             {6, 8, 11}};
        pln("printMatrix1 : ");
        plns(printMatrix(matrix1));
        pln("printMatrix2 : ");
        plns(printMatrix(matrix2));
        pln("printMatrix3 : ");
        plns(printMatrix(matrix3));
        pln("printMatrix4 : ");
        plns(printMatrix(matrix4));
        pln("printMatrix5 : ");
        plns(printMatrix(matrix5));
        pln("printMatrix : ");
        plns(printMatrix(matrix));
        //plns(arrays);
    }
}
