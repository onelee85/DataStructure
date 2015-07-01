package com.james.ds.list;

import java.util.LinkedList;

import static com.james.ds.Utils.pn;

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

    public void print(){
        for (T item : link) {
            pn(item+"\t");
        }
    }
}
