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

    public static void main(String[] args) {
        pln("isAnagram :" + isAnagram("evil", "live"));
    }
}
