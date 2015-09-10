package com.james.ds.tree;

import static com.james.ds.Utils.pln;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		TreeNode<String> nodeA = new TreeNode<String>("a");
		TreeNode<String> nodeB = new TreeNode<String>("b");
		TreeNode<String> nodeC = new TreeNode<String>("c");
		TreeNode<String> nodeD = new TreeNode<String>("d");
		TreeNode<String> nodeE = new TreeNode<String>("e");
		TreeNode<String> nodeF = new TreeNode<String>("f");
		TreeNode<String> nodeG = new TreeNode<String>("g");

		nodeA.setLeftChild(nodeB);
		nodeA.setRightChild(nodeD);
		nodeB.setLeftChild(nodeC);
		nodeD.setLeftChild(nodeE);
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

        pln("the deep of tree :" + BinaryTree.deep(binaryTree.root()));

		pln("++++++++++++++++++二叉搜索树++++++++++++++++++");
		Integer[] arrs = { 53, 60, 18, 21, 22, 82, 74, 79, 12, 88 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < arrs.length; i++) {
			TreeNode<Integer> node = new TreeNode<Integer>(arrs[i]);
			bst.insert(node);
		}
		binaryTree.travIn(bst.root());
		pln();

		pln(bst.find(21).getData());
		pln(bst.findMin().getData());
		pln(bst.findMax().getData());

		bst.delete(88);
		binaryTree.travIn(bst.root());
		pln();

        int[] seqs = {5,7,6,9,11,10,8};
        Integer[] seqs2 = {7,4,6,5};
        pln("判断是否搜索树："+ BinarySearchTree.verifySquenceOfBST(seqs, 0, seqs.length-1));

        pln("++++++++++++++++++AVL平衡搜索树++++++++++++++++++");
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        for (int i = 0; i < arrs.length; i++) {
            TreeNode<Integer> node = new TreeNode<Integer>(arrs[i]);
            avlTree.insert(node);
        }
        binaryTree.travIn(avlTree.root());
        pln();


        pln("++++++++++++++++++堆++++++++++++++++++");
        Heap<Integer> heap = new Heap<Integer>(10);
        heap.insert(5);
        heap.insert(6);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.print();
        heap.insert(15);
        heap.print();
        heap.insert(16);
        heap.print();
        pln("getMax:"+heap.getMax());
        heap.delMax();//16
        heap.print();
        heap.delMax();//15
        heap.print();
        heap.delMax();//6
        heap.print();
        heap.delMax();//5
        heap.print();
        heap.delMax();//4
        heap.delMax();//3
        pln("getMax after delMax :"+heap.getMax());
        heap.print();
        pln();
	}
}
