package com.james.ds.link;

/**
 * 链表节点
 */
public class LinkedNode<T> {
    private T data;
    private LinkedNode<T> link = null;

    public LinkedNode(){}
    public LinkedNode(T data){
        this.data = data;
    }

    /**
     * 后继节点
     * @param nextNode
     */
    public void pointNext(LinkedNode<T> nextNode){
        this.link = nextNode;
    }

    /**
     * 返回后继节点
     * @return
     */
    public LinkedNode<T> next(){
        return this.link;
    }
}
