package com.james.ds.tree;

import com.james.ds.list.Stack;

import static com.james.ds.Utils.*;

/**
 * 二叉树
 */
public class BinaryTree{

    TreeNode root = null;
    public BinaryTree(TreeNode root){
        this.root = root;
    }
    public TreeNode root() {
        return this.root;
    }

    /**
     * 前序周游  递归
     */
    public void travRecurPre(TreeNode node){
        if(node  == null) return;
        visit(node);
        travRecurPre(node.getLiftChild());
        travRecurPre(node.getRightChild());
    }

    /**
     * 中序周游   递归
     */
    public void travRecurIn(TreeNode node){
        if(node  == null) return;
        travRecurIn(node.getLiftChild());
        visit(node);
        travRecurIn(node.getRightChild());
    }

    /**
     * 后续周游   递归
     */
    public void travRecurPost(TreeNode node){
        if(node  == null) return;
        travRecurPost(node.getLiftChild());
        travRecurPost(node.getRightChild());
        visit(node);
    }

    /**
     * 层次周游
     */
    public void travLeve(){}

    /**
     * 非递归前序周游
     */
    public void travPre(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode currNode = null;
        if(node != null){
            currNode = node;
        }
        //根节点开始访问
        while (currNode != null){
            visit(currNode);
            TreeNode lchild = currNode.getLiftChild();
            if( lchild != null){//如果有左子树 将根节点压入栈中 访问左子树根节点
                stack.push(currNode);
                currNode = lchild;
                continue;
            }
            TreeNode rchild = currNode.getRightChild();
            while (rchild == null && !stack.isEmpty()){
                rchild = stack.popTop().getRightChild();
            }
            currNode = rchild;
        }

    }

    /**
     * 非递归中序周游
     */
    public void travIn(TreeNode node){
    }

    /**
     * 非递归中序周游
     */
    public void travPost(TreeNode node){
    }

    public void visit(TreeNode node){
        pn(node.getData() + " ");
    }
}
