package com.james.ds;

import java.util.regex.Pattern;

/**
 * @author: jiao.li
 * Date: 2015/6/30 11:09
 */
public class Utils {

    static Pattern p = Pattern.compile("[0-9]");

    public static void pln(Object content){
        System.out.println(content);
    }

    public static Boolean isDigit(char c){
        return p.matcher(String.valueOf(c)).matches();
    }
}
