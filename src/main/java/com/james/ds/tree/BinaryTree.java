package com.james.ds.tree;

import com.james.ds.list.Queue;
import com.james.ds.list.Stack;
import com.james.ds.tree.TreeNode.Tag;

import static com.james.ds.Utils.*;

/**
 * 二叉树
 */
public class BinaryTree {

	TreeNode root = null;

	public BinaryTree(TreeNode root) {
		this.root = root;
	}

	public TreeNode root() {
		return this.root;
	}

	/**
	 * 前序周游 递归
	 */
	public void travRecurPre(TreeNode node) {
		if (node == null)
			return;
		visit(node);
		travRecurPre(node.getLeftChild());
		travRecurPre(node.getRightChild());
	}

	/**
	 * 中序周游 递归
	 */
	public void travRecurIn(TreeNode node) {
		if (node == null)
			return;
		travRecurIn(node.getLeftChild());
		visit(node);
		travRecurIn(node.getRightChild());
	}

	/**
	 * 后续周游 递归
	 */
	public void travRecurPost(TreeNode node) {
		if (node == null)
			return;
		travRecurPost(node.getLeftChild());
		travRecurPost(node.getRightChild());
		visit(node);
	}

	/**
	 * 非递归前序周游
	 */
	public void travPre(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode currNode = null;
		if (node != null) {
			currNode = node;
		}
		// 根节点开始访问
		while (currNode != null || !stack.isEmpty()) {
			if (currNode == null) {
				currNode = stack.popTop().getRightChild();
				continue;
			}
			visit(currNode);
			TreeNode lchild = currNode.getLeftChild();
			if (lchild != null) {// 如果有左子树 将根节点压入栈中 访问左子树根节点
				stack.push(currNode);
				currNode = lchild;
				continue;
			}
			currNode = currNode.getRightChild();
		}

	}

	/**
	 * 非递归前序周游2
	 */
	public void travPre_v2(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (node != null) {
			stack.push(node);
		}
		// 根节点开始访问
		while (!stack.isEmpty()) {
			TreeNode currNode = stack.popTop();
			visit(currNode);
			// 注意先右后左入栈， 才能保证接下先弹出左节点
			TreeNode rchild = currNode.getRightChild();
			if (rchild != null) {// 如果有左子树 将根节点压入栈中 访问左子树根节点
				stack.push(rchild);
			}
			TreeNode lchild = currNode.getLeftChild();
			if (lchild != null) {// 如果有左子树 将根节点压入栈中 访问左子树根节点
				stack.push(lchild);
			}
		}

	}

	/**
	 * 沿着左侧链向下访问，收集右侧节点
	 */
	private void visitAlongLeft(Stack<TreeNode> stack, TreeNode node) {
		while (node != null) {
			visit(node);
			TreeNode rchild = node.getRightChild();
			if (rchild != null) {// 如果有左子树 将根节点压入栈中 访问左子树根节点
				stack.push(rchild);
			}
			node = node.getLeftChild();
		}

	}

	/**
	 * 非递归前序周游3 沿左侧链访问
	 */
	public void travPre_v3(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (node != null) {
			stack.push(node);
		}
		do {
			// 弹出访问左侧链收集的右侧节点
			node = stack.popTop();
			// 访问左侧链
			visitAlongLeft(stack, node);
		} while (!stack.isEmpty());
	}

	/**
	 * 非递归中序周游
	 */
	public void travIn(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode currNode = null;
		if (node != null) {
			currNode = node;
			stack.push(currNode);
		}
		// 根节点开始访问
		while (currNode != null || !stack.isEmpty()) {
			if (currNode == null) {
				currNode = stack.popTop();
				visit(currNode);
				currNode = currNode.getRightChild();
				if (currNode != null) {
					stack.push(currNode);
				}
				continue;
			}
			TreeNode lchild = currNode.getLeftChild();
			if (lchild != null) {// 如果有左子树 将根节点压入栈中 访问左子树根节点
				stack.push(lchild);
			}
			currNode = lchild;

		}
	}

	/**
	 * 沿着左侧链向下寻找左侧节点 并入栈
	 */
	private void goAlongLeftBranch(Stack<TreeNode> stack, TreeNode node) {
		while (node != null) {
			stack.push(node);
			node = node.getLeftChild();
		}

	}

	/**
	 * 非递归中序周游 V2
	 */
	public void travIn_v2(TreeNode node) {
		TreeNode currNode = null;
		if (node != null) {
			currNode = node;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		// 根节点开始访问依次访问各个节点
		do {
			// 保存当前节点的左侧链上节点， 直到访问左侧叶子节点
			goAlongLeftBranch(stack, currNode);
			// 弹出最后入栈的子节点 并访问
			currNode = stack.popTop();
			visit(currNode);
			// 获得当前节点右子树
			currNode = currNode.getRightChild();
		} while (currNode != null || !stack.isEmpty());
	}

	/**
	 * 非递归后序周游
	 */
	public void travPost(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Tag tag = null;
		TreeNode currNode = null;
		if (node != null) {
			currNode = node;
			stack.push(currNode);
		}
		// 根节点开始访问
		while (currNode != null || !stack.isEmpty()) {
			if (currNode != null && currNode.getTag() == null) {
				TreeNode lchild = currNode.getLeftChild();
				if (lchild != null) {
					currNode.setTag(Tag.Left);
					stack.push(lchild);
				}
				currNode = lchild;
			} else {
				currNode = stack.popTop();
				if (currNode.getTag() == null || currNode.getTag() != Tag.Right) {
					TreeNode rchild = currNode.getRightChild();
					if (rchild != null) {
						currNode.setTag(Tag.Right);
						stack.push(currNode);
						stack.push(rchild);
						currNode = rchild;
						continue;
					}
				}
				visit(currNode);
				currNode = null;
			}

		}
	}

	/**
	 * 层次周游
	 */
	public void travLeve(TreeNode node) {
		Queue<TreeNode> queue = new Queue<TreeNode>();
		if (node != null) {
			queue.enQueue(node);
		}
		TreeNode currNode = null;
		while (!queue.isEmpty()) {
			currNode = queue.deQueue();
			visit(currNode);
			if (currNode.getLeftChild() != null)
				queue.enQueue(currNode.getLeftChild());
			if (currNode.getRightChild() != null)
				queue.enQueue(currNode.getRightChild());
		}
	}

	public void visit(TreeNode node) {
		pn(node.getData() + " ");
	}
}
