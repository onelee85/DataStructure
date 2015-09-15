package com.james.ds.usage.seq;

import java.util.*;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;
import static com.james.ds.Utils.pn;

/**
 * 字符串相关
 */
public class StrSeq {

    /**
     * 判断2个字符串是否为互变词,每个单词出现的字母和次数相同 入 evil 和 live
     * @param str1
     * @param str2
     * @return
     */
    public static Boolean isAnagram(String str1, String str2){
        Map<String, Integer> countMap = new HashMap<String, Integer>(str1.length());
        char[] arrs1 =  str1.toCharArray();
        char[] arrs2 =  str2.toCharArray();
        for (int i = 0; i < arrs1.length ; i++) {
            Integer count = countMap.get(String.valueOf(arrs1[i]));
            if(count == null){
                count = 1;
            }
            countMap.put(String.valueOf(arrs1[i]), count);
        }

        for (int i = 0; i < arrs2.length ; i++) {
            Integer count = countMap.get(String.valueOf(arrs2[i]));
            if(count != null){
                --count;
            }else{
                count = 1;
            }
            countMap.put(String.valueOf(arrs2[i]), count);
        }
        for (Integer v : countMap.values()){
            if(v != 0)
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 请实现一个函数，将一个字符串中的空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * 解：
     * 先计算出来字符串空格的数量
     * 计算出替换为%20的新字符串的长度,计算出需要替换的空间
     * "We Are Happy     "
     *             ↑    ↑
     *             p1   p2
     * 复制p1指向的字符 移动p1,p2  遇到空格插入%20 3个字符
     */
    public static String replaceSpace(StringBuffer str) {
        if(str == null || str.length() == 0) return "";
        int numOfBlank = 0;
        int oriLength = str.length();
        for (int i = 0; i < oriLength; i++) {
            if(str.charAt(i) == ' ') numOfBlank++;
        }
        if(numOfBlank == 0) return str.toString();
        int newLength = str.length() + numOfBlank * 2;
        str.setLength(newLength);
        int endIndex = newLength - 1;
        for (int i = oriLength - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if(c == ' ') {
                str.setCharAt(endIndex--, '0');
                str.setCharAt(endIndex--, '2');
                str.setCharAt(endIndex--, '%');
            }else{
                str.setCharAt(endIndex--, c);
            }
        }
        return str.toString();
    }

    static char [] seqs;
    static Integer [] book;
    static HashSet<String> result = new HashSet<String>();
    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c
     * 所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。 结果请按字母顺序输出。
       输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。\
       解：

     * @param str
     * @return
     */
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> arrange = new ArrayList<String>();
        if(str == null || str.isEmpty()) return arrange;
        char[] strs = str.toCharArray();
        seqs = new char[strs.length];
        book = new Integer[strs.length];
        for (int i = 0; i < book.length; i++) {
            book[i] = 0;
        }
        dfs(strs, 0);
        arrange.addAll(result);
        Collections.sort(arrange);
        return arrange;
    }

    /**
     * 深度遍历法
     */
    static void dfs(char[] arrs, int step){
        //走完所有可能，打印
        if(arrs.length == step){
            String str = "";
            for (int i = 0; i < seqs.length; i++) {
                str += seqs[i];
            }
            result.add(str);
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

    public static void main(String[] args) {
        pln("isAnagram :" + isAnagram("evil", "live"));

        pln("replaceSpace  We Are Happy:" + replaceSpace(new StringBuffer("We Are Happy")));

        pln("Permutation");
        plns(Permutation("abc"));
    }
}
