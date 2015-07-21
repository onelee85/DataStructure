package com.james.ds.list;


import static com.james.ds.Utils.pln;

public class Test {

    public static void main(String[] args) {
        Link<Integer> linklist = new Link<Integer>();
        linklist.add(1);
        linklist.add(2);
        linklist.add(3);
        linklist.add(4);
        linklist.add(5);
        linklist.printList();
        System.out.println();
        pln("reverse link");
        linklist.reverse();
        linklist.printList();
        System.out.println();
        System.out.println("find data :" + linklist.find(2));
        linklist.remove(2);
        linklist.printList();

        Stack<Integer> stack = new Stack<Integer>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println();
        System.out.println("popTop :" + stack.popTop());
        System.out.println("popTop :" + stack.popTop());
        System.out.println("popTop :" + stack.popTop());
        System.out.println("popTop :" + stack.popTop());

        Queue<Integer> queue = new Queue<Integer>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

        System.out.println("dequeue :" + queue.deQueue());
        System.out.println("dequeue :" + queue.deQueue());
        System.out.println("dequeue :" + queue.deQueue());
    }
}
