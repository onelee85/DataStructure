package com.james.ds.usage.tree;

import com.james.ds.list.Stack;
import com.james.ds.tree.BinaryTree;
import com.james.ds.tree.TreeNode;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.pn;

/**
 * 输入一个数值，打印出二叉树中路径到叶子节点值的和为输出值的路径
 */
public class FindPathOfSum {

    private static void FindPtah(BinaryTree binaryTree, Integer value){
        Stack<Integer> path = new Stack<Integer>();
        FindPath(binaryTree.root(), value, path, 0);
    }

    public static void FindPath(TreeNode node, Integer expectSum, Stack<Integer> path, Integer currentSum) {
        Integer curr_val = (Integer) node.getData();
        currentSum += curr_val;
        path.push(curr_val);
        //如果为叶子节点 并且当前值等于期待值 则打印路径
        Boolean isLead = node.getLeftChild() == null && node.getRightChild() == null;
        if(isLead && expectSum.equals(currentSum)){
            pln("path is found:");
            path.print();
            pln();
        }

        TreeNode lchild = node.getLeftChild();
        if (lchild != null) {// 如果有左子树
            FindPath(lchild, expectSum, path, currentSum);
        }

        TreeNode rchild = node.getRightChild();
        if (rchild != null) {// 如果有右子树
            FindPath(rchild, expectSum, path, currentSum);
        }
        //回退至上一节点
        currentSum -= curr_val;
        path.popTop();
    }

    public static void main(String[] args) {
        TreeNode<Integer> nodea = new TreeNode<Integer>(10);
        TreeNode<Integer> nodeb = new TreeNode<Integer>(5);
        TreeNode<Integer> nodec = new TreeNode<Integer>(12);
        TreeNode<Integer> nodee = new TreeNode<Integer>(4);
        TreeNode<Integer> nodef = new TreeNode<Integer>(7);

        nodea.setLeftChild(nodeb);
        nodea.setRightChild(nodec);
        nodeb.setLeftChild(nodee);
        nodeb.setRightChild(nodef);

        BinaryTree binaryTree = new BinaryTree(nodea);

        //打印输出值为22的路径
        FindPtah(binaryTree, 22);
    }
}
