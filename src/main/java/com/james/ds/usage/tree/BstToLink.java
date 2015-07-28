package com.james.ds.usage.tree;

import com.james.ds.list.Stack;
import com.james.ds.tree.BinarySearchTree;
import com.james.ds.tree.BinaryTree;
import com.james.ds.tree.TreeNode;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.pn;

/**
 * 二叉搜索树转换成有序双向链表，不能创建新节点，只能调整指针方向
 */
public class BstToLink {

    private static TreeNode<Integer> lastNodeInLink = null;

    private static TreeNode<Integer> convert(TreeNode<Integer> root){
        //树节点左指针指向前一个节点，右指针指向后一个节点
        //中旬遍历二叉搜索树从大到小的每个节点，
        //遍历到根节点时，其左子树的右叶子节点为左子树最大值 连接根节点
        //同理，右子树左叶子节点则为最小
        convertReturnNode(root);
        TreeNode<Integer> headNode = lastNodeInLink;
        //返回头节点
        while(headNode.getLeftChild() != null){
            headNode = headNode.getLeftChild();
        }
        return headNode;
    }

    private static void convertReturnNode(TreeNode<Integer> currRoot){
        if(currRoot == null) return ;
        //如果当前节点有左子树 遍历左子树
        if(currRoot.getLeftChild() != null)
            convertReturnNode(currRoot.getLeftChild());

        //左子树遍历完 当前节点一定比链表尾部指针大
        currRoot.setLeftChild(lastNodeInLink);// 左指针指向尾部指针节点
        if(lastNodeInLink != null)
            lastNodeInLink.setRightChild(currRoot); //如果链表尾部指针节点存在 尾部指针节点右指针指向当前节点

        //移动链表尾节点指向当前节点
        lastNodeInLink = currRoot;

        //继续遍历右子树
        if(currRoot.getRightChild() != null)
            convertReturnNode(currRoot.getRightChild());
    }

    public static void main(String[] args) {
        Integer[] arrs = { 53, 60, 18, 21, 22, 82, 74, 79, 12, 88 };
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for (int i = 0; i < arrs.length; i++) {
            TreeNode<Integer> node = new TreeNode<Integer>(arrs[i]);
            bst.insert(node);
        }
        BinaryTree.travIn(bst.root());
        pln();
        TreeNode<Integer> headNode = convert(bst.root());
        //打印链表
        while(headNode != null){
            pn(headNode.getData() + " ");
            headNode = headNode.getRightChild();
        }
        pln();
        //反向打印
        TreeNode<Integer> lastNode = lastNodeInLink;
        while(lastNode != null){
            pn(lastNode.getData() + " ");
            lastNode = lastNode.getLeftChild();
        }
    }
}
