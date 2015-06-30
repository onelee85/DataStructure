package com.james.ds.list;

import java.util.LinkedList;

/**
 * 队列
 */
public class Queue<T> {
    private LinkedList<T> link = new LinkedList<T>();

    public void enQueue(T t){
        link.add(t);
    }

    public T deQueue(){
        if(link.size() == 0) return null;
        return link.remove(0);
    }
}
