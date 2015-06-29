package com.james.ds.list;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2015/6/29 15:34
 */
public interface List<E> {

    public void add(E e);

    public Boolean remove(E e);

    public E find(E e);

    public void printList();
}
