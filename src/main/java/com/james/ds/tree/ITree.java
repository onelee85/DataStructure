package com.james.ds.tree;

/**
 * 树接口
 */
public interface ITree<T> {
    public TreeNode<T> root();
    public void insert(TreeNode<T> t);
    public void remove(int i);
    public void remove(TreeNode<T> t);
    public void traverse();
}
