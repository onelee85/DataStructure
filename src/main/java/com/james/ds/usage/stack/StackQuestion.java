package com.james.ds.usage.stack;

import java.util.Stack;

import static com.james.ds.Utils.pln;

/**
 * 栈相关算法问题
 */
public class StackQuestion {

    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    static class StackAsQuene {

        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        StackAsQuene squene = new StackAsQuene();
        squene.push(1);
        squene.push(2);
        squene.push(3);
        pln(squene.pop());
        pln(squene.pop());

        squene.push(4);
        pln(squene.pop());
        squene.push(5);
        pln(squene.pop());
        pln(squene.pop());
    }
}
