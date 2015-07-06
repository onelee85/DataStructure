package com.james.ds.tree;

import java.util.Comparator;

/**
 * 二叉搜索树
 * 用于操作：动态搜索、添加、删除元素
 * 有节点K, 其左节点小于K，其右节点大于K， 且对于其子树也是一样
 */
public class BinarySearchTree<T> {

	TreeNode<T> root = null;

	public BinarySearchTree(TreeNode<T> root) {
		this.root = root;
	}

	public BinarySearchTree() {
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

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	private int compare(TreeNode<T> node1, TreeNode<T> node2) {
		return comparator.compare(node1.getData(), node2.getData());
	}

	private int compare(T data1, T data2) {
		return comparator.compare(data1, data2);
	}

	public TreeNode<T> root() {
		return this.root;
	}

	public TreeNode<T> find(T value) {
        if (root != null) {
            TreeNode<T> currNode = root;
            while (currNode != null) {
                int compare = compare(value, currNode.getData());
                if (compare < 0) { // 小于于当前节点左叶子树上
                    currNode = currNode.getLeftChild();
                } else if (compare > 0) {
                    currNode = currNode.getRightChild();
                } else {
                    return currNode;
                }
            }
        }
        return null;
    }

	public TreeNode<T> findMin() {
		return findMin(this.root);
	}

	public TreeNode<T> findMin(TreeNode<T> treeNode) {
		if (treeNode != null) {
			TreeNode<T> currNode = treeNode;
			while (currNode.getLeftChild() != null) {
				currNode = currNode.getLeftChild();
			}
			return currNode;
		}
		return null;
	}

	public TreeNode<T> findMax() {
		return findMax(this.root);
	}

	public TreeNode<T> findMax(TreeNode<T> treeNode) {
		if (treeNode != null) {
			TreeNode<T> currNode = treeNode;
			while (currNode.getRightChild() != null) {
				currNode = currNode.getRightChild();
			}
			return currNode;
		}
		return null;
	}

	public void insert(TreeNode<T> newNode) {
		if (root == null) {
			root = newNode;
			return;
		}
		TreeNode<T> currNode = root;
		while (currNode != null) {
			if (compare(currNode, newNode) < 0) { // 大于当前节点放在右叶子树上
				if (currNode.getRightChild() != null) {
					currNode = currNode.getRightChild();
					continue;
				}
				currNode.setRightChild(newNode);
			} else if (compare(currNode, newNode) > 0) {
				if (currNode.getLeftChild() != null) {
					currNode = currNode.getLeftChild();
					continue;
				}
				currNode.setLeftChild(newNode);
			}
			break;
		}
	}

	public void delete(T value) {
		this.root = delete(this.root, value);
	}

	public TreeNode<T> delete(TreeNode<T> root, T value) {
		TreeNode<T> tmpNode = null;
		if (compare(root.getData(), value) < 0) {// 删除节点大于根节点，继续沿着右子树向下找
			root.setRightChild(delete(root.getRightChild(), value));
		} else if (compare(root.getData(), value) > 0) {
			root.setLeftChild(delete(root.getLeftChild(), value));
		} else {// 找到需要删除节点
				// 如果删除节点有左右子树，找到左子树的最大节点，或者右子树的最小节点替换删除节点
			if (root.getLeftChild() != null && root.getRightChild() != null) {
				tmpNode = findMax(root.getLeftChild());
				root.setData(tmpNode.getData());//替换当前要删除节点
				root.setLeftChild(delete(root.getLeftChild(), tmpNode.getData()));//删除之前替换的节点
			} else if (root.getLeftChild() != null) {// 如果删除节点有左子树，将删除节的父节点指向左子树
				root.setLeftChild(root.getLeftChild());
			} else if (root.getRightChild() != null) {// 如果删除节点有右子树，将删除节的父节点指向右子树
				root.setRightChild(root.getRightChild());
			} else {// 如果为叶节点直接删除
				root = null;
			}
		}
		return root;
	}
}
