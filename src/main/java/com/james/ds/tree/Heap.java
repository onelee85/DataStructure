package com.james.ds.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import static com.james.ds.Utils.pn;

/**
 * 优先队列 堆
 */
public class Heap<T> {

    Vector<T> v = new Vector<T>();

    private Object[] elementData;
    private Integer elementCount = 0;
    public Heap(int size){
        elementData = new Object[size];
    }

    public T getMax(){
        if(elementData.length == 0) return null;
        return (T) elementData[0];
    }

    /**
     * 删除根节点
     * 队尾元素换到根节点， 然后下滤比较 是否小与子节点
     * 小则和较大的子节点交换
     */
    public void delMax(){
        if(elementCount == 0){
            return;
        }
        //交换根节点和叶子节点
        elementData[0] = null;
        elementCount--;
        elementData[0] = elementData[elementCount];
        elementData[elementCount] = null;
        //下滤比较
        if(elementCount > 1){
            for (int i = 0; i < elementCount / 2;){
                int max_index = i;
                if(comparator.compare((T)elementData[i*2+1], (T)elementData[max_index]) > 0){
                    max_index = i*2+1;
                }

                if(comparator.compare((T)elementData[i*2+2], (T)elementData[max_index]) > 0){
                    max_index = i*2+2;
                }
                if(max_index != i){
                    swap((T)elementData[max_index], (T)elementData[i]);
                    i = max_index;
                    continue;
                }
                return;
            }
        }

    }

    /**
     * 插入节点
     * 节点作为叶节点插入到队尾， 然后跟父节点比较是大小， 大则交换
     */
    public void insert(T node) {
        elementData[elementCount++] = node;
        T parent = getParent(node);
        while (parent != null) {
            if (comparator.compare(node, parent) > 0) {
                swap(parent, node);
                parent = getParent(node);
            }else{
                return;
            }

        }
    }

    /**
     * 获得父亲节点
     * @param node
     * @return
     */
    public T getParent(T node){
        int index = indexOf(node);
        int parent = 0;
        if(index >= 1){
            parent = (index+1)/2 - 1;
        }
        return (T)elementData[parent];
    }

    private void swap(T node1 , T node2){
        int index1 = indexOf(node1);
        int index2 = indexOf(node2);
        elementData[index1] = node2;
        elementData[index2] = node1;
    }

    public void print(){
        for (Object item : elementData) {
            if(item != null)
                pn((T)item+"\t");
        }
    }

    public synchronized int indexOf(Object o) {
        if (o == null) {
            for (int i = 0 ; i < elementData.length ; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0 ; i < elementData.length ; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }


    /**
     * 默认比较器
     */
    private Comparator<T> comparator = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
        if (o1 instanceof Integer) {
            return (Integer) o1 - (Integer) o2;
        }
        return 0;
        }
    };
}
