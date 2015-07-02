package com.james.ds.list;

import java.util.LinkedList;

import static com.james.ds.Utils.pn;

/**
 * 队列
 */
public class Queue<T> {
    private LinkedList<T> link = new LinkedList<T>();

    /**
     * 队列首元素
     * @return
     */
    public T front(){
        if(link.size() == 0) return null;
        return link.getFirst();
    }

    /**
     * 入队
     * @param t
     */
    public void enQueue(T t){
        link.addLast(t);
    }

    /**
     * 出队
     * @return
     */
    public T deQueue(){
        if(link.size() == 0) return null;
        return link.removeFirst();
    }

    public Boolean isEmpty(){
        return link.size() == 0;
    }

    public Integer size(){
        return link.size();
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
