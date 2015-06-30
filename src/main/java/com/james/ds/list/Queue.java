package com.james.ds.list;

import java.util.LinkedList;

/**
 * 队列
 */
public class Queue<T> {
    private LinkedList<T> link = new LinkedList<T>();

    public void enQueue(T t){
        link.addLast(t);
    }

    public T deQueue(){
        if(link.size() == 0) return null;
        return link.removeFirst();
    }

    public Boolean isEmpty(){
        return link.size() == 0;
    }

    public void clear(){
        link.clear();
    }
}