package com.james.ds.list;


import static com.james.ds.Utils.pln;

public class Test {

    public static void main(String[] args) {
        Link<Integer> linklist = new Link<Integer>();
        linklist.add(1);
        linklist.add(2);
        linklist.add(5);
        linklist.add(6);
        linklist.add(9);
        linklist.printList();
        Link<Integer> linklist2 = new Link<Integer>();
        linklist2.add(3);
        linklist2.add(4);
        linklist2.add(7);
        linklist2.add(8);
        linklist2.add(10);
        linklist2.printList();

        pln("reverse link");
        linklist.reverse();
        linklist.printList();

        pln("reverse link");
        linklist.reverseV2();
        linklist.printList();

        pln("merge sorted link1 to link2");
        Link<Integer> linklist3 = linklist.mergeSortLink(linklist, linklist2);
        //Link<Integer> linklist3 = linklist.mergeSortLinkV2(linklist, linklist2);
        linklist3.printList();
        pln("find data :" + linklist.find(2));
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
