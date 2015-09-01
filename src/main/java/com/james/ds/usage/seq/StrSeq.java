package com.james.ds.usage.seq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;

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


    public static void main(String[] args) {
        pln("isAnagram :" + isAnagram("evil", "live"));

        pln("replaceSpace  We Are Happy:" + replaceSpace(new StringBuffer("We Are Happy")));
    }
}
