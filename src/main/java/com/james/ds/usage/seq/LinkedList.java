package com.james.ds.usage.seq;

import com.james.ds.list.Link;
import com.james.ds.list.LinkedNode;

import java.util.ArrayList;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;

/**
 * 链表类算法问题
 */
public class LinkedList {

    /**
     * 找到两个链表的公共节点
     * 1.找到较长的链表，先移动到和短链表相同位置
     * 2.然后同步前移，比较是否有相同节点
     * @param linklist1
     * @param linklist2
     */
    private static LinkedNode<Integer> findCommonNode(Link<Integer> linklist1, Link<Integer> linklist2){
        LinkedNode<Integer> firstCommonNode = null;
        LinkedNode<Integer> longhead = linklist1.getHead();
        LinkedNode<Integer> shorthead = linklist2.getHead();
        Integer moveLength = linklist1.size() - linklist2.size();
        if(linklist2.size() > linklist1.size() ){
            shorthead = linklist1.getHead();
            longhead = linklist2.getHead();
            moveLength =  linklist2.size() -  linklist1.size();
        }
        while (moveLength-- > 0){
            longhead = longhead.next();
        }
        while (longhead.hasNext() && shorthead.hasNext()
                && longhead != shorthead ){
            longhead = longhead.next();
            shorthead = shorthead.next();
        }
        firstCommonNode = longhead;
        return firstCommonNode;
    }




    /**
     * 输入一个链表，从尾到头打印链表每个节点的值。
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> stacks  = new ArrayList<Integer>();
        ArrayList<Integer> list  = new ArrayList<Integer>();

        while (listNode != null){
            stacks.add(listNode.val);
            listNode = listNode.next;
        }
        if(stacks.size() > 0){
            for (int i = stacks.size() -1 ; i >= 0 ; i--) {
                list.add(stacks.get(i));
            }
        }

        return list;
    }

    public static void main(String[] args) {
        LinkedNode<Integer> node1 = new LinkedNode<Integer>(1);
        LinkedNode<Integer> node2 = new LinkedNode<Integer>(2);
        LinkedNode<Integer> node3 = new LinkedNode<Integer>(3);
        LinkedNode<Integer> node4 = new LinkedNode<Integer>(4);
        LinkedNode<Integer> node5 = new LinkedNode<Integer>(5);
        LinkedNode<Integer> node6 = new LinkedNode<Integer>(6);
        LinkedNode<Integer> node7 = new LinkedNode<Integer>(7);
        LinkedNode<Integer> node8 = new LinkedNode<Integer>(8);
        LinkedNode<Integer> node9 = new LinkedNode<Integer>(9);
        Link<Integer> linklist = new Link<Integer>();
        linklist.add(node1);
        linklist.add(node2);
        linklist.add(node4);
        linklist.add(node8);
        linklist.add(node9);
        linklist.printList();
        Link<Integer> linklist2 = new Link<Integer>();
        linklist2.add(node3);
        linklist2.add(node5);
        linklist2.add(node6);
        linklist2.add(node7);
        linklist2.add(node8);
        linklist2.printList();
        pln(findCommonNode(linklist, linklist2).getData());

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        pln("printListFromTailToHead :");
        plns(printListFromTailToHead(n1));
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}