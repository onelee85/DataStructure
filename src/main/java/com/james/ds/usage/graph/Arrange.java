package com.james.ds.usage.graph;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.pn;

/**
 * 求排列组合
 * 列： 1，2，3 的全排列   abc字符串的全排列  八皇后问题
 */
public class Arrange {

    /**
     * 穷举法
     * @param seqs
     */
    static void exhaustion(Integer[] seqs) {
        for (int i = 0; i < seqs.length; i++) {
            for (int j = 0; j < seqs.length; j++) {
                for (int k = 0; k < seqs.length; k++) {
                    if (!seqs[i].equals(seqs[j]) && !seqs[i].equals(seqs[k]) && !seqs[j].equals(seqs[k])) {
                        pln(seqs[i] +""+ seqs[j] +""+ seqs[k]);
                    }
                }
            }
        }
    }

    static Object [] seqs;
    static Integer [] book;

    /**
     * 深度遍历法
     */
    static void dfs(Object[] arrs, int step){
        //走完所有可能，打印
        if(arrs.length == step){
            for (int i = 0; i < seqs.length; i++) {
                pn(seqs[i] +" ");
            }
            pln();
            return; //返回上一步
        }
        //遍历整个序列,尝试每一种可能
        for (int i = 0; i < arrs.length; i++) {
            //是否走过
            if(book[i] == 0){
                seqs[step] = arrs[i];
                book[i] = 1;
                //下一步
                dfs(arrs, step + 1);
                //走完最后一步 后退一步
                book[i] = 0;
            }
        }
    }

    //是否找到结果
    static Boolean foundResult = Boolean.FALSE;
    //所有的皇后列索引全排列组合
    static Integer [] roundSeqs;
    /**
     * 八皇后问题
     * @param num 皇后数量
     */
    static void queen(int num){
        Integer[] columnIndex = new Integer[num];
        for (int i = 0; i < num; i++) {
            columnIndex[i] = i;
        }
        roundSeqs = new Integer[columnIndex.length];
        book = new Integer[columnIndex.length];
        for (int i = 0; i < book.length; i++) {
            book[i] = 0;
        }
        queen_move(columnIndex, 0);
    }

    /**
     * 每个皇后肯定不在同一行
     * 定义一个列数组 columnIndex 保存每个皇后的列号,
     * 对列数组进行全排列
     * 判断每个皇后是不是在同一对角线上
     */
    static void queen_move(Integer[] arrs, int step){
        if(foundResult) return;
        //走完所有可能
        if(arrs.length == step){
            //是否所有皇后位置合理
            if(isPerfect(roundSeqs)){
                foundResult = Boolean.TRUE;
                printQueen(roundSeqs);
            }
            return; //返回上一步
        }
        //遍历整个序列,尝试每一种可能
        for (int i = 0; i < arrs.length; i++) {
            if(foundResult) return;
            //是否走过
            if(book[i] == 0){
                roundSeqs[step] = arrs[i];
                book[i] = 1;//标记走过的位置
                //下一步
                queen_move(arrs, step + 1);
                //走完最后一步 后退一步
                book[i] = 0;
            }
        }
    }

    //判断是否位置是否合理
    static Boolean isPerfect(Integer[] roundSeqs){
        for (int i = 0; i < roundSeqs.length ; i++) {
            int curr_column = roundSeqs[i];
            for (int j = 0; j < roundSeqs.length ; j++) {
                if(j != i){
                    int otherColumn = roundSeqs[j];
                    //如果为同一列 则不合法
                    if(curr_column == otherColumn){
                        return Boolean.FALSE;
                    }
                    // 是否为对角线
                    if(j - i == otherColumn - curr_column || i - j == otherColumn - curr_column){
                        return Boolean.FALSE;
                    }
                }

            }
        }
        return Boolean.TRUE;
    }

    //打印皇后位置
    static void printQueen(Integer[] roundSeqs){

        for (int i = 0; i < roundSeqs.length; i++) {
            for (int j = 0; j < roundSeqs.length; j++) {
                int value = 0;
                if(j == roundSeqs[i]){
                    value = 1;
                }
                pn(value +" ");
            }
            pln();
        }

    }

    public static void main(String[] args) {
        Integer[] arrs = {1,2,3};
        String[] strs = {"a","b","c"};
        //exhaustion(arrs);
        seqs = new Object[strs.length];
        book = new Integer[strs.length];
        for (int i = 0; i < book.length; i++) {
            book[i] = 0;
        }
        //dfs(strs, 0);
        //八皇后
        queen(8);
    }
}
