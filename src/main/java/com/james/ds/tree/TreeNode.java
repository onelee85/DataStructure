package com.james.ds.tree;

import java.util.Comparator;

/**
 * 二叉树树节点
 */
public class TreeNode<T> {
	enum Tag {
		Left, Right;
	}

	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	private Tag tag;
    private TreeNode<T> parent;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public TreeNode(T data) {
		this.data = data;
	}

	public TreeNode<T> getLeftChild() {
		return this.leftChild;
	}

	public TreeNode<T> getRightChild() {
		return this.rightChild;
	}
	public TreeNode<T> getParent() {
		return this.parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
