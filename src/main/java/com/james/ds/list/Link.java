package com.james.ds.list;

import com.james.ds.tree.TreeNode;

import java.util.Comparator;

import static com.james.ds.Utils.pln;

/**
 * 链表
 */
public class Link<T> implements List<T>{

    private LinkedNode<T> head = new LinkedNode<T>();
    private LinkedNode<T> tail = new LinkedNode<T>();

    public void add(T t){
        LinkedNode<T> node = new LinkedNode<T>(t);
        if(!head.hasNext()){
            head.pointNext(node);
            node.pointPre(head);
            tail.pointNext(node);
            return;
        }
        LinkedNode<T> tailNode = tail.next();
        tailNode.pointNext(node);
        node.pointPre(tailNode);
        tail.pointNext(node);
    }

    public Boolean remove(T t){
        LinkedNode<T> curr = head;
        while (curr.hasNext()){
            curr = curr.next();
            if(curr.getData().equals(t)){
                curr.pre().pointNext(curr.next());
                if(curr.hasNext()){
                    curr.next().pointPre(curr.pre());
                }
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public T find(T t){
        LinkedNode<T> curr = head;
        while (curr.hasNext()){
            curr = curr.next();
            if(curr.getData().equals(t)){
                return curr.getData();
            }
        }
        return null;
    }


    /**
     * 反转链表
     */
    public void reverse(){
        LinkedNode<T> curr = head.next();
        //前驱节点
        LinkedNode<T> pre = null;
        tail.pointNext(head);
        while (curr != null){
            //头节点指向当前节点
            head.pointNext(curr);
            //临时保存当前节点的后继节点
            LinkedNode<T> next = curr.next();
            //当前节点指向前驱节点
            curr.pointNext(pre);
            if(pre != null)
                pre.pointPre(curr);
            pre = curr;
            curr = next;
        }
    }

    /**
     * 递归反转
     */
    public void reverseV2(){
        reverse(head.next(), null);
    }

    private void reverse(LinkedNode<T> curr, LinkedNode<T> pre){
        if(curr == null) return;
        head.pointNext(curr);
        LinkedNode<T> next = curr.next();
        curr.pointNext(pre);
        if(pre != null)
            pre.pointPre(curr);
        reverse(next, curr);
    }

    /**
     * 合并有序列表 并保持有序
     */
    public Link<T> mergeSortLink(Link<T> linklist1, Link<T> linklist2){
        Link<T> linklist = new Link<T>();
        LinkedNode<T> head1 = linklist1.head.next();
        LinkedNode<T> head2 = linklist2.head.next();
        while (head1 != null && head2 != null){
            if(compare(head1, head2) < 0){
                linklist.add(head1.getData());
                head1 = head1.next();
            }else{
                linklist.add(head2.getData());
                head2 = head2.next();
            }
        }
        while (head1 != null){
            linklist.add(head1.getData());
            head1 = head1.next();
        }
        while (head2 != null){
            linklist.add(head2.getData());
            head2 = head2.next();
        }
        return linklist;
    }

    /**
     * 合并2个有序链表 递归版本
     */
    public Link<T> mergeSortLinkV2(Link<T> linklist1, Link<T> linklist2){
        Link<T> linklist = new Link<T>();
        LinkedNode<T> head1 = linklist1.head.next();
        LinkedNode<T> head2 = linklist2.head.next();
        linklist.head.pointNext(mergeSort(head1, head2));
        return linklist;
    }

    private LinkedNode<T> mergeSort(LinkedNode<T> head1, LinkedNode<T> head2){
        if(head1 == null)
            return head2;
        else if(head2 == null)
            return head1;
        LinkedNode<T> mergeHead = null;
        //比较2个节点大小
        if(compare(head1, head2) < 0){
            mergeHead = head1;// 指向小的节点
            //后继节点指向剩余的合并头节点
            mergeHead.pointNext(mergeSort(head1.next(), head2));
        }else{
            mergeHead = head2;
            mergeHead.pointNext(mergeSort(head1, head2.next()));
        }
        return mergeHead;
    }

    public void printList(){
        LinkedNode<T> curr = head;
        while (curr.hasNext()){
            curr = curr.next();
            System.out.print(curr.getData() + " ");
        }
        pln();
    }

    private Comparator<T> comparator = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            if (o1 instanceof Integer) {
                return (Integer) o1 - (Integer) o2;
            }
            return 0;
        }
    };

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(LinkedNode<T> node1, LinkedNode<T> node2) {
        return comparator.compare(node1.getData(), node2.getData());
    }
}
