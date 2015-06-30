package com.james.ds.usage.stack;

import com.james.ds.list.Stack;

/**
 * 1.递归嵌套  具有自相似性
 *
 * 括号匹配, HTML 标签匹配 <a><a/>
 */
public class Matching {
    final static String left = "(";
    final static String right = ")";

    /**
     * 左括号入栈， 遇右扩号弹出
     * @param expression
     * @return
     */
    public static Boolean match(String expression){
        Stack<String> s = new Stack<String>();
        for(char c :expression.toCharArray()){
            String str = c+"";
            if(str.equals(left))
                s.push(str);
            else if(str.equals(right) && s.popTop() == null){
                    return Boolean.FALSE;
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(match("((1+1)+((2+2)+3)+4+5)"));
        System.out.println(match("()()()))"));
        System.out.println(match("(()()()))"));
        System.out.println(match("((()()()))"));
        System.out.println(match("(((()())()))"));

    }
}
