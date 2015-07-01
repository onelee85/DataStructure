package com.james.ds;

import java.util.regex.Pattern;

/**
 * @author: jiao.li
 * Date: 2015/6/30 11:09
 */
public class Utils {

    static Pattern p = Pattern.compile("[0-9]");
    static Pattern p_num = Pattern.compile("^\\d+$");

    public static void pln(Object content){
        System.out.println(content);
    }
    public static void pn(Object content){
        System.out.print(content);
    }

    public static Boolean isDigit(char c){
        return p.matcher(String.valueOf(c)).matches();
    }

    public static Boolean isDigit(String str){
        return p_num.matcher(str).matches();
    }
}
