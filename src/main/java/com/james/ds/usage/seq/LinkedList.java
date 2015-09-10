package com.james.ds.usage.seq;

import com.james.ds.list.Link;
import com.james.ds.list.LinkedNode;

import java.util.ArrayList;
import java.util.List;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;
import static com.james.ds.Utils.pn;

/**
 * 链表类算法问题
 */
public class LinkedList {

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
        public void prints(){
            ListNode curr = this;
            while (curr != null){
                pn(curr.val + " ");
                curr = curr.next;
            }
            pln();
        }
    }

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

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode head,int k) {
        if(head == null || k <= 0) return null;
        ListNode currKOfNode = head;
        int currCount = 1;
        while (head.next != null){
            currCount++;
            if(currCount - 1 - k >= 0){
                currKOfNode = currKOfNode.next;
            }
            head = head.next;

        }
        if(currCount < k) return null;
        return currKOfNode;
    }

    /**
     * 输入一个链表，反转链表后，输出链表的所有元素。
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        if(head == null) return null;
        ListNode curr = head;

        //前驱节点
        ListNode pre = null;
        while (curr != null){
            //临时保存当前节点的后继节点
            ListNode next = curr.next;
            //当前节点指向前驱节点
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge(ListNode list1,ListNode list2) {
        ListNode linkHead = null;
        ListNode linktail = null;
        if(list1 == null) return list2;
        else if(list2 == null) return list1;
        else {
            if(list1.val < list2.val){
                linkHead = list1;
                list1 = list1.next;
            }else{
                linkHead = list2;
                list2 = list2.next;
            }
            linktail = linkHead;
            //linkHead = (list1.val < list2.val) ? list1 : list2;
        }

        while (list1 != null && list2 != null){
            if(list1.val < list2.val){
                linktail.next = list1;
                linktail = list1;
                list1 = list1.next;
            }else{
                linktail.next = list2;
                linktail = list2;
                list2 = list2.next;
            }
        }
        while (list1 != null){
            linktail.next = list1;
            linktail = list1;
            list1 = list1.next;
        }
        while (list2 != null){
            linktail.next = list2;
            linktail = list2;
            list2 = list2.next;
        }
        return linkHead;
    }

    static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }

        public static void prints(RandomListNode head){
            List<Integer> list = new ArrayList<Integer>();
            while (head != null){
                list.add(head.label);
                if(head.random != null){
                    pn(head.label + " -> " + head.random.label + ",  ");
                }
                head = head.next;
            }
            pln();
            for (Integer v : list){
                pn(v + " -> ");
            }
        }
    }

    /**
     *输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）。
     * @param pHead
     * @return
     */
    public static RandomListNode Clone(RandomListNode pHead){
        if(pHead == null) return null;
        copyNewListNode(pHead);
        connectRandomNode(pHead);
        return splitNode(pHead);
        //return null;
    }

    /**
     * 先复制主要指针的节点
     * 复制每个链表节点指向复制的节点
     * A->A`->B->B`->C->C`
     * @param pHead
     */
    private static void copyNewListNode(RandomListNode pHead){
        RandomListNode pNode = pHead;
        while(pNode != null){
            RandomListNode newNode = new RandomListNode(pNode.label);
            RandomListNode preNext = pNode.next;
            pNode.next = newNode;
            newNode.next = preNext;
            pNode = preNext;
        }
    }

    /**
     * 链接特殊指针
     * 那么复制出来的node是前一个特殊指针指向的下一个节点
     * @param pHead
     */
    private static void connectRandomNode(RandomListNode pHead){
        RandomListNode pNode = pHead;
        while(pNode != null){
            RandomListNode copyNode = pNode.next;
            if(pNode.random != null)
                copyNode.random = pNode.random.next;
            pNode = copyNode.next;
        }
    }

    /**
     * 拆分链表
     * @param pHead
     * @return
     */
    private static RandomListNode splitNode(RandomListNode pHead){
        RandomListNode pNode = pHead;
        RandomListNode pNewNode = pHead.next;
        RandomListNode pNewHead = pNewNode;
        while(pNode != null && pNewNode != null){
            if(pNode.next != null){
                pNode.next = pNode.next.next;
            }
            pNode = pNode.next;
            if(pNewNode.next != null){
                pNewNode.next = pNewNode.next.next;
            }
            pNewNode = pNewNode.next;
        }
        return pNewHead;
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
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        pln("printListFromTailToHead :");
        plns(printListFromTailToHead(n1));


        pln("FindKthToTail :");
        FindKthToTail(n1, 5).prints();

        pln("ReverseList :");
        ReverseList(n5).prints();


        pln("Merge sort link list :");
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        ListNode n9 = new ListNode(9);
        ListNode n10 = new ListNode(10);
        ListNode n11 = new ListNode(11);
        ListNode n12 = new ListNode(12);
        ListNode n13 = new ListNode(13);

        n7.next = n10;
        n10.next = n12;
        n12.next = n13;
        n7.prints();
        n8.next = n9;
        n9.next = n11;
        n8.prints();
        Merge(n7, n8).prints();

        RandomListNode rnode1 = new RandomListNode(1);
        RandomListNode rnode2 = new RandomListNode(2);
        RandomListNode rnode3 = new RandomListNode(3);
        RandomListNode rnode4 = new RandomListNode(4);
        RandomListNode rnode5 = new RandomListNode(5);
        rnode1.next = rnode2;
        rnode2.next = rnode3;
        rnode3.next = rnode4;
        rnode4.next = rnode5;
        rnode1.random = rnode1;
        rnode4.random = rnode2;
        rnode2.random = rnode4;
        RandomListNode.prints(rnode1);
        pln("\n Clone-----");
        RandomListNode newNode = Clone(rnode1);
        RandomListNode.prints(newNode);
    }
}

