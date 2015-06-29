package com.james.ds.list;

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

    public void printList(){
        LinkedNode<T> curr = head;
        while (curr.hasNext()){
            curr = curr.next();
            System.out.print(curr.getData() + " ");
        }
    }
}
