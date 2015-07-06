package com.james.ds.graph;

import static com.james.ds.Utils.pln;
import static com.james.ds.Utils.pn;

/**
 * 图：多对多关系
 *
 * Vertex 顶点
 *
 * Edge 边
 *
 * G(V, E) 由一个非空的有限顶点V集合和一个有限边E组成
 */
public class Graph {


    //邻接矩阵：G[N][N]二位矩阵，适合稠密图，稀疏图则存在大量无效元素,统计边的条数比较浪费时间
    private Integer[][] graphs = null;

    //邻接表: G[n] 对应矩阵每一行一个链表 适合稀疏图
    private Node[] graphLink = null;

    class Node{
        public Integer data;
        public Node next;
        public Node(Integer data){
            this.data = data;
        }

    }
    public Graph(int n){
        this.graphs = new Integer[n][n];
        for (int i = 0; i < graphs.length; i++){
            for (int j = 0; j < graphs[i].length; j++){
                graphs[i][j] = 0;
            }
        }
        this.graphLink = new Node[n];

    }

    public void insert(int v1, int v2){
        graphs[v1][v2] = 1;
        graphs[v2][v1] = 1;
    }

    public void insertGraphLink(int v1, int v2){
        Node node1 = graphLink[v1];
        Node node2 = new Node(v2);
        if(node1 == null){
            node1 = new Node(v1);
            graphLink[v1] = node1;
        }
        while(node1.next != null){
            node1 = node1.next;
        }
        node1.next = node2;
    }

    public void print(){
        for (int i = 0; i < graphs.length; i++){
            for (int j = 0; j < graphs[i].length; j++){
                pn(graphs[i][j] + " ");
            }
            pln();
        }
    }

    public void printGraphLink(){
        for (int i = 0; i < graphLink.length; i++){
            Node node = graphLink[i];
            while(node != null){
                pn(node.data + " ");
                node = node.next;
            }
            pln();
        }
    }
}
