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
        char[] sentences = str.toCharArray();
        //先翻转整个句子
        reverseSeq(sentences);
        //pln(String.valueOf(sentences));

        String[] words = String.valueOf(sentences).split(" ");
        for (String world : words){
            char[] w = world.toCharArray();
            //翻转句子的每个单词
            reverseSeq(w);
            pn(String.valueOf(w) + " ");
        }

    }

    private static void reverseSeq(char[] sentences){
        for (int i = 0, j = sentences.length-1; i < sentences.length / 2 ; i++, j--) {
            char tmp = sentences[i];
            sentences[i] = sentences[j];
            sentences[j] = tmp;
        }
    }
    public static void main(String[] args) {
        reverseSentence("I am student.");
    }
}
