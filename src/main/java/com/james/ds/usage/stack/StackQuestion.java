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

    /**
     *定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
     */
    static class StackWithMin {

        private Stack<Integer> stack = new Stack<Integer>();
        private Stack<Integer> min_stack = new Stack<Integer>();
        private Integer min = null;
        public void push(int node) {
            stack.push(node);
            //判断当前值是否小于辅助栈中当前最小值
            if(min == null || node < min){
                min = node;
            }
            //保证顶点上总是最小值
            min_stack.push(min);
        }

        public void pop() {
            stack.pop();
            min_stack.pop();//同时弹出辅助栈
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min_stack.peek();
        }
    }

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     * @return
     */
    public static boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA == null || pushA.length == 0 ||
                popA == null || popA.length != pushA.length)
            return Boolean.FALSE;
        int[] stack = new int[pushA.length];
        int topIndex = -1;
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            //第一个序列入栈
            stack[++topIndex] = pushA[i];
            //判断栈顶元素是不是和popA当前元素相等
            if(stack[topIndex] ==  popA[popIndex]){
                //相等则弹出栈顶元素
                topIndex--;
                popIndex++;
            }
        }
        while (topIndex >= 0){
            if(stack[topIndex--] !=  popA[popIndex++])
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
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

        StackWithMin stackWithMin = new StackWithMin();
        stackWithMin.push(4);
        stackWithMin.push(3);
        stackWithMin.push(2);
        stackWithMin.push(5);
        stackWithMin.push(1);
        stackWithMin.push(8);
        pln("top : "+stackWithMin.top());
        pln("min : "+stackWithMin.min());
        stackWithMin.pop();
        pln("top after pop  : "+stackWithMin.top());
        stackWithMin.pop();
        pln("min after pop  : "+stackWithMin.min());

        int [] pushA = {1,2,3,4,5};
        int [] popA = {4,5,3,2,1};
        pln("IsPopOrder  : " + IsPopOrder(pushA, popA));
    }
}
