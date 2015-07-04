package com.james.ds.tree;


import static com.james.ds.Utils.pln;

public class Test {

    public static void main(String[] args) {
        TreeNode<String> nodeA = new TreeNode<String>("a");
        TreeNode<String> nodeB = new TreeNode<String>("b");
        TreeNode<String> nodeC = new TreeNode<String>("c");
        TreeNode<String> nodeD = new TreeNode<String>("d");
        TreeNode<String> nodeE = new TreeNode<String>("e");
        TreeNode<String> nodeF = new TreeNode<String>("f");
        TreeNode<String> nodeG = new TreeNode<String>("g");

        nodeA.setLiftChild(nodeB);
        nodeA.setRightChild(nodeD);
        nodeB.setLiftChild(nodeC);
        nodeD.setLiftChild(nodeE);
        nodeD.setRightChild(nodeF);
        nodeF.setRightChild(nodeG);

        BinaryTree binaryTree = new BinaryTree(nodeA);
        pln("++++++++++++++++++递归周游++++++++++++++++++");
        binaryTree.travRecurPre(binaryTree.root());
        pln();
        binaryTree.travRecurIn(binaryTree.root());
        pln();
        binaryTree.travRecurPost(binaryTree.root());
        pln();
        pln("++++++++++++++++++非递归周游++++++++++++++++++");
        binaryTree.travPre(binaryTree.root());
        pln();
        binaryTree.travPre_v2(binaryTree.root());
        pln();
        binaryTree.travPre_v3(binaryTree.root());
        pln();

        binaryTree.travIn(binaryTree.root());
        pln();
        binaryTree.travIn_v2(binaryTree.root());
        pln();

        binaryTree.travPost(binaryTree.root());
        pln();

        binaryTree.travLeve(binaryTree.root());
        pln();
    }
}
