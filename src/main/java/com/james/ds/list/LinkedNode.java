package com.james.ds.list;

/**
 * 链表节点
 */
public class LinkedNode<T> {
    private T data;

    private LinkedNode<T> next = null;
    private LinkedNode<T> pre = null;

    public LinkedNode(){}
    public LinkedNode(T data){
        this.data = data;
    }

    /**
     * 后继节点
     * @param nextNode
     */
    public void pointNext(LinkedNode<T> nextNode){
        this.next = nextNode;
    }

    /**
     * 前驱节点
     * @param nextNode
     */
    public void pointPre(LinkedNode<T> nextNode){
        this.pre = nextNode;
    }
    /**
     * 返回前驱
     * @return
     */
    public LinkedNode<T> pre(){
        return this.pre;
    }

    /**
     * 返回后继节点
     * @return
     */
    public LinkedNode<T> next(){
        return this.next;
    }

    public Boolean hasNext(){
        return this.next != null;
    }

    public T getData(){
        return this.data;
    }
}
