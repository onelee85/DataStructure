package com.james.ds.tree;

/**
 * 二叉树树节点
 */
public class TreeNode<T> {
    private T data;
    private TreeNode<T> liftChild;
    private TreeNode<T> rightChild;

    public TreeNode(T data){
        this.data = data;
    }

    public TreeNode<T> getLiftChild(){
        return  this.liftChild;
    }

    public TreeNode<T> getRightChild(){
        return  this.rightChild;
    }

    public void setLiftChild(TreeNode<T> liftChild){
        this.liftChild = liftChild;
    }

    public void setRightChild(TreeNode<T> rightChild){
        this.rightChild = rightChild;
    }

    public T getData(){
        return this.data;
    }

}
