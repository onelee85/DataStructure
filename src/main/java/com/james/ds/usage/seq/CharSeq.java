package com.james.ds.usage.seq;

import static com.james.ds.Utils.*;
import static com.james.ds.Utils.pln;

/**
 * 字符序列相关
 */
public class CharSeq {

    /**
     * 翻转句子 I am student
     * 1.先翻转句子 tneduts ma I
     * 2.再翻转回每个单词 student am I
     * @param str
     */
    public static void reverseSentence(String str){
        if(str != null){
            //先翻转整个句子
            str = reverseSeq(str, 0, str.length()-1);
            //pln(String.valueOf(sentences));

            String[] words = String.valueOf(str).split(" ");
            for (String world : words){
                //翻转句子的每个单词
                world = reverseSeq(world, 0, world.length()-1);
                pn(world + " ");
            }
        }
    }

    /**
     * 字符串左旋转  把字符串若干个字符转移到字符串尾部
     * 例如：abcdefg 和数字2 返回旋转2位的结果  cdefgab
     * 解：
     * 先旋转前半部分字符串，再旋转后半部分
     * 最后旋转整个字符串
     * @param str
     */
    public static String leftRatateString(String str, Integer n){
        if(str != null && str.length() > 0 && str.length() > n){
            //先翻转前半部分
            str = reverseSeq(str, 0, n-1);
            //再翻转后半部分
            str = reverseSeq(str, n, str.length()-1);
            //翻转整个字符串
            str = reverseSeq(str, 0, str.length()-1);
        }
        return str;
    }

    private static String reverseSeq(String str, int begin, int end){
        char[] sentences = str.toCharArray();
        for (int i = begin, j = end; i < j ; i++, j--) {
            char tmp = sentences[i];
            sentences[i] = sentences[j];
            sentences[j] = tmp;
        }
        return String.valueOf(sentences);
    }

    public static void main(String[] args) {
        reverseSentence("I am student.");
        pln();
        pln(leftRatateString("abcXYZdef", 1));

    }
}
