package com.james.ds.list;

import java.util.ArrayList;

/**
 *栈
 */
public class Stack<T> {

    private ArrayList<T> arr = new ArrayList<T>();

    public Stack(){}
    public Stack(int size){
        this.arr = new ArrayList<T>(size);
    }

    public void push(T t){
        arr.add(t);
    }

    public T popTop(){
        if(arr.size() == 0) return null;
        return arr.remove(arr.size()-1);
    }

    public Boolean isEmpty(){
        return arr.size() == 0;
    }
}
