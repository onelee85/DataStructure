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
        while (currNode != null || !stack.isEmpty()){
            if(currNode == null) {
                currNode = stack.popTop().getRightChild();
                continue;
            }
            visit(currNode);
            TreeNode lchild = currNode.getLiftChild();
            if( lchild != null){//如果有左子树 将根节点压入栈中 访问左子树根节点
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
    public void travPre_v2(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(node != null){
            stack.push(node);
        }
        //根节点开始访问
        while (!stack.isEmpty()){
            TreeNode currNode = stack.popTop();
            visit(currNode);
            //注意先右后左入栈， 才能保证接下先弹出左节点
            TreeNode rchild = currNode.getRightChild();
            if( rchild != null){//如果有左子树 将根节点压入栈中 访问左子树根节点
                stack.push(rchild);
            }
            TreeNode lchild = currNode.getLiftChild();
            if( lchild != null){//如果有左子树 将根节点压入栈中 访问左子树根节点
                stack.push(lchild);
            }
        }

    }

    /**
     * 沿着左侧链向下访问，收集右侧节点
     * @param stack
     * @param node
     */
    private void visitAlongLeft(Stack<TreeNode> stack, TreeNode node){
        while(node != null){
            visit(node);
            TreeNode rchild = node.getRightChild();
            if( rchild != null){//如果有左子树 将根节点压入栈中 访问左子树根节点
                stack.push(rchild);
            }
            node = node.getLiftChild();
        }

    }

    /**
     * 非递归前序周游3 沿左侧链访问
     */
    public void travPre_v3(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(node != null){
            stack.push(node);
        }
        do{
            //弹出访问左侧链收集的右侧节点
            node = stack.popTop();
            //访问左侧链
            visitAlongLeft(stack, node);
        }while (!stack.isEmpty());
    }



    /**
     * 非递归中序周游
     */
    public void travIn(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode currNode = null;
        if(node != null){
            currNode = node;
            stack.push(currNode);
        }
        //根节点开始访问
        while (currNode != null || !stack.isEmpty()){
            if(currNode == null) {
                currNode = stack.popTop();
                visit(currNode);
                currNode = currNode.getRightChild();
                if( currNode != null){//如果有左子树 将根节点压入栈中 访问左子树根节点
                    stack.push(currNode);
                }
                continue;
            }
            TreeNode lchild = currNode.getLiftChild();
            if( lchild != null){//如果有左子树 将根节点压入栈中 访问左子树根节点
                stack.push(lchild);
            }
            currNode = lchild;

        }
    }

    /**
     * 沿着左侧链向下寻找左侧节点 并入栈
     * @param stack
     * @param node
     */
    private void goAlongLeftBranch(Stack<TreeNode> stack, TreeNode node){
        while(node != null){
            stack.push(node);
            node = node.getLiftChild();
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
        //根节点开始访问

        do {
            goAlongLeftBranch(stack, currNode);
            currNode = stack.popTop();
            visit(currNode);
            currNode = currNode.getRightChild();
        } while (currNode != null || !stack.isEmpty());
    }

    /**
     * 非递归后序周游
     */
    public void travPost(TreeNode node){

    }

    public void visit(TreeNode node){
        pn(node.getData() + " ");
    }
}
