package com.james.ds.usage.tree;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.pn;

/**
 * 树相关题目
 */
public class TreeQuestion {

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
     * 则重建二叉树并返回。
     * 解:
     * 分析出前序和中序的规律
     * {1, 2,4,7,        3,5,6,8}
     * {   4,7,2,   1,   5,3,8,6}
     */
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null || pre.length == 0 || in.length == 0 )
            return null;
        return constructBinaryTree(pre, 0, pre.length-1, in, 0, in.length-1);
    }

    /**
     *
     * 递归前序序列和中序序列得到根节点
     * @return
     */
    public static TreeNode constructBinaryTree(int [] pre, int preBeginIndex, int preEndIndex,
                                                 int [] in,  int inBeginIndex, int inEndIndex) {
        //前序遍历的第一个节点为根节点
        int rootVla = pre[preBeginIndex];
        TreeNode root = new TreeNode(rootVla);
        if(preBeginIndex == preEndIndex){
            if(inBeginIndex == inEndIndex && pre[preBeginIndex] == in[inBeginIndex]){
                return root;
            }else {
                return null;
            }
        }
        int newInRootIndex = inBeginIndex;
        //找到根节点在中序列中的位置
        while (newInRootIndex <= inEndIndex && in[newInRootIndex] != rootVla)
            ++newInRootIndex;
        //中序左节点数量
        int inLeftCount = newInRootIndex - inBeginIndex;
        //前序节点截止位置
        int preLeftEnd = preBeginIndex + inLeftCount;

        //存在左子树
        if(inLeftCount > 0)
            root.left = constructBinaryTree(pre, (preBeginIndex + 1), preLeftEnd,
                                            in,   inBeginIndex,  newInRootIndex-1);

        if(preLeftEnd < preEndIndex)
            root.right = constructBinaryTree(pre, (preLeftEnd + 1), preEndIndex,
                                            in,   newInRootIndex + 1,  inEndIndex);
        return root;
    }

    public static void main(String[] args) {
        int [] pre = {1,2,4,7,3,5,6,8};
        int [] in = {4,7,2,1,5,3,8,6};
        pln("reConstructBinaryTree:");
        TreeNode root = reConstructBinaryTree(pre, in);
        TreeNode.printByPre(root);
        pln();
        TreeNode.printByIn(root);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public static void printByPre(TreeNode node){
       if(node == null) return;
       pn(node.val + ",");
       printByPre(node.left);
       printByPre(node.right);
    }

    public static void printByIn(TreeNode node){
        if(node == null) return;
        printByPre(node.left);
        pn(node.val + ",");
        printByPre(node.right);
    }
}
