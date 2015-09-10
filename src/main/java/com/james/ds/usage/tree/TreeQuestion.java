package com.james.ds.usage.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.plns;
import static com.james.ds.Utils.pn;

/**
 * 树相关题目
 */
public class TreeQuestion {

    static class TreeNode {
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

    /**
     * 输入两颗二叉树A，B，判断B是不是A的子结构。
     * 解：
     * 1.找到相等的root
     * 2.判断其子树是否相同
     * 3.如果不其子树，则继续遍历A树
     * @param root1
     * @param root2
     * @return
     */
    public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
        Boolean res = Boolean.FALSE;
        if(root1 != null && root2 != null){
            if(root1.val == root2.val)
                res = hasSameTree(root1, root2);
            if(!res)
                res = HasSubtree(root1.left, root2);
            if(!res)
                res = HasSubtree(root1.right, root2);
        }
        return res;
    }

    private static Boolean hasSameTree(TreeNode root1,TreeNode root2){
        if(root2 == null) return Boolean.TRUE;

        if(root1 == null) return Boolean.FALSE;

        if(root1.val != root2.val)
            return Boolean.FALSE;

        return hasSameTree(root1.left, root2.left) &&
                hasSameTree(root1.right, root2.right);
    }

    /**
     *操作给定的二叉树，将其变换为源二叉树的镜像。
     * @param root
     */
    public static void Mirror(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return;
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        if(root.left != null){
            Mirror(root.left);
        }
        if(root.right != null){
            Mirror(root.right);
        }
    }

    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     * 解:层次遍历
     * @param root
     * @return
     */
    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            list.add(node.val);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
        return list;
    }

    /**
     * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * @param root
     * @param target
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        if(root == null || target <= 0) return list;
        ArrayList<Integer> path = new ArrayList<Integer>();
        FindPath(list, root, target, path, 0);
        return list;
    }

    public static void FindPath(ArrayList<ArrayList<Integer>> list, TreeNode node, int expectSum, ArrayList<Integer> path, int currentSum) {
        int curr_val = node.val;
        currentSum += curr_val;
        path.add(curr_val);
        //如果为叶子节点 并且当前值等于期待值 则记录路径
        Boolean isLead = node.left == null && node.right == null;
        if(isLead && expectSum == currentSum){
            ArrayList<Integer> onePath = new ArrayList<Integer>();
            onePath.addAll(path);
            list.add(onePath);
        }

        TreeNode lchild = node.left;
        if (lchild != null) {// 如果有左子树
            FindPath(list, lchild, expectSum, path, currentSum);
        }

        TreeNode rchild = node.right;
        if (rchild != null) {// 如果有右子树
            FindPath(list, rchild, expectSum, path, currentSum);
        }
        //回退至上一节点
        currentSum -= curr_val;
        if(!path.isEmpty())
            path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        int [] pre = {1,2,4,7,3,5,6,8};
        int [] in = {4,7,2,1,5,3,8,6};
        pln("reConstructBinaryTree:");
        TreeNode root = reConstructBinaryTree(pre, in);
        TreeNode.printByPre(root);
        pln();
        TreeNode.printByIn(root);
        pln();

        TreeNode rootA = new TreeNode(8);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        rootA.left = node8;
        rootA.right = new TreeNode(7);
        node8.left = node9;
        node8.right = node2;
        node2.left = node4;
        node2.right = node7;

        TreeNode rootB = new TreeNode(8);
        rootB.left = new TreeNode(1);
        rootB.right = new TreeNode(2);

        pln("hasSameTree:"+ HasSubtree(rootA, rootB));

        //8,6,10,5,7,9,11
        //8,6,5,7,10,9,11,
        TreeNode root1 = new TreeNode(8);
        TreeNode n6 = new TreeNode(6);
        TreeNode n10 = new TreeNode(10);
        TreeNode n5= new TreeNode(5);
        TreeNode n7= new TreeNode(7);
        TreeNode n9= new TreeNode(9);
        TreeNode n11= new TreeNode(11);
        root1.left = n6;
        root1.right = n7;
        n6.left = n10;
        n6.right = n5;
        n7.left = n9;
        n7.right = n11;
        TreeNode.printByPre(root1);pln();
        Mirror(root1);
        TreeNode.printByPre(root1);
        pln();
        pln("PrintFromTopToBottom:");
        plns(PrintFromTopToBottom(rootA));
        pln();
        pln("FindSumOfPath:");
        ArrayList<ArrayList<Integer>> list = FindPath(rootA, 15);
        for (ArrayList<Integer> path : list){
            plns(path);
        }
    }
}
