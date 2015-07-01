package com.james.ds.usage.stack;

import com.james.ds.list.Queue;
import com.james.ds.list.Stack;

import static com.james.ds.Utils.isDigit;
import static com.james.ds.Utils.pln;

/**
 * 逆波兰表达式
 */
public class RePolishExpression {
    enum Operator{
        NULL,ADD,SUB,MUL,DIV,LP,RP;
    }
                                           //NULL  +    -    *    /    (    )
    private static final char[][] PRI = {   {'=', '<', '<', '<', '<', '<', ' '},//NULL
                                            {'>', '>', '>', '<', '<', '<', '>'},//+
                                            {'>', '>', '>', '<', '<', '<', '>'},//-
                                            {'>', '>', '>', '>', '>', '<', '>'},//*
                                            {'>', '>', '>', '>', '>', '<', '>'},// /
                                            {' ', '<', '<', '<', '<', ' ', '='},//(
                                            {' ', ' ', ' ', ' ', ' ', ' ', ' '}//)

                                    };
    public static void readNum(Stack<Integer> num_statck, Stack<Integer> num_child_s, Integer num){
        //连续数字判断子串是否为空
        if(!num_child_s.isEmpty()){
            num_statck.push(num_statck.popTop() * 10 + num);
            return;
        }
        num_child_s.push(num);
        num_statck.push(num);
    }

    public static int getOperator(char op) {
        switch (op) {
            case '+':
                return Operator.ADD.ordinal();
            case '-':
                return Operator.SUB.ordinal();
            case '*':
                return Operator.MUL.ordinal();
            case '/':
                return Operator.DIV.ordinal();
            case '(':
                return Operator.LP.ordinal();
            case ')':
                return Operator.RP.ordinal();
            case '\0':
                return Operator.NULL.ordinal();
        }
        return -1;
    }

    public static Integer caculate(Integer num1, char opt, Integer num2){
        Integer result = 0;
        switch (opt) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '(':
                return 0;
            case ')':
                return 0;
        }
        return result;
    }

    public static Queue<String> reverseRPN(String expression) {
        expression = expression+"\0";
        Queue<String> RPN = new Queue<String>();
        Stack<Integer> num_statck = new Stack<Integer>(); //操作数栈
        Stack<Integer> num_child_s = new Stack<Integer>(); //操作数栈子栈
        Stack<String> opt_statck = new Stack<String>(); //操作符栈
        opt_statck.push(String.valueOf('\0'));
        int index = 0;
        char[] chars = expression.toCharArray();
        while (!opt_statck.isEmpty()) {
            char c = chars[index];
            String str = String.valueOf(c);
            if (isDigit(c)) {
                //将数字读入栈内
                readNum(num_statck, num_child_s, Integer.valueOf(str));
                index++;
                continue;
            }
            //清除操作数
            if(!num_child_s.isEmpty()){
                RPN.enQueue(num_statck.top().toString());
                num_child_s.clear();
            }
            if (!opt_statck.isEmpty()) {

                int optr1 = getOperator(opt_statck.top().charAt(0));
                int optr2 = getOperator(c);
                char pri = PRI[optr1][optr2];
                switch (pri) {
                    case '>':
                        //比较之前栈内操作数优先级
                        //如果比之前优先级小或者相等，则弹出2个操作数和1个操作符进行计算
                        //Integer num2 = num_statck.popTop();
                        //Integer num1 = num_statck.popTop();
                        String opt = opt_statck.popTop();
                        //num_statck.push(caculate(num1, opt.charAt(0), num2));
                        RPN.enQueue(opt);
                        break;
                    case '<': //操作符继续入栈
                        opt_statck.push(String.valueOf(c));
                        index++;
                        break;
                    case '=':
                        opt_statck.popTop();
                        index++;
                        break;
                }
            }
        }
        num_statck.clear();
        opt_statck.clear();
        return RPN;
    }

    public static String evaluate(Queue<String> RPN){
        Stack<String> opt_statck = new Stack<String>();
        while(!RPN.isEmpty()){
            String val = RPN.deQueue();
            if (isDigit(val)) {//操作数入栈
                opt_statck.push(val);
            }else{
                Integer num2 = Integer.valueOf(opt_statck.popTop());
                Integer num1 = Integer.valueOf(opt_statck.popTop());
                opt_statck.push(caculate(num1, val.charAt(0), num2).toString());
            }
        }
        return opt_statck.popTop();
    }

    public static void main(String[] args) {
        Queue<String> RPN = reverseRPN("(11+2*5)*(2+1)");
        RPN.print();
        pln("");
        pln(evaluate(RPN));
    }
}
