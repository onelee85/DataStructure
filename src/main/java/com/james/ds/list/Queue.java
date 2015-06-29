package com.james.ds.list;

import java.util.LinkedList;

/**
 * @author: jiao.li
 * Date: 2015/6/29 16:02
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
