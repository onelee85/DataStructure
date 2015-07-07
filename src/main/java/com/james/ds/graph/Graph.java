package com.james.ds.graph;

import com.james.ds.list.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private Integer nums = 0;
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
        this.nums = n;
        this.graphs = new Integer[n][n];
        for (int i = 0; i < graphs.length; i++){
            for (int j = 0; j < graphs[i].length; j++){
                graphs[i][j] = 0;
            }
        }
        this.graphLink = new Node[n];

    }

    /**
     * 节点数量
     * @return
     */
    private Integer getNum(){
        return this.nums;
    }

    public void insert(int v1, int v2){
        graphs[v1][v2] = 1;
        graphs[v2][v1] = 1;
    }

    public void insertGraphLink(int v1, int v2){
        Node node1 = graphLink[v1];
        Node node1_2 = new Node(v2);
        if(node1 == null){
            node1 = new Node(v1);
            graphLink[v1] = node1;
        }
        while(node1.next != null){
            node1 = node1.next;
        }
        node1.next = node1_2;

        Node node2 = graphLink[v2];
        Node node2_1 = new Node(v1);
        if(node2 == null){
            node2 = new Node(v2);
            graphLink[v2] = node2;
        }
        while(node2.next != null){
            node2 = node2.next;
        }
        node2.next = node2_1;
    }

    /**
     * 寻找节点 从邻接表
     * @param n
     * @return
     */
    public Node findGraphLink(int n){
        for (int i = 0; i < graphLink.length; i++){
            Node node = graphLink[i];
            if(node != null && node.data == n)
                return node;
        }
        return null;
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
            if(graphLink[i] != null)
                pln();
        }
    }

    /**
     * 深度优先
     * 邻接表 O(N + E)
     * 邻接矩阵 O(N^2)
     */
    public void DFS(int start_num) {
        //找到起点
        List<Integer> visited = new ArrayList<Integer>(nums);
        Node v_node = findGraphLink(start_num);
        Stack<Node> stack = new Stack<Node>();
        stack.push(v_node);
        while (v_node != null || !stack.isEmpty()) {
            if (v_node != null) {
                v_node = findGraphLink(v_node.data);
                visit(v_node);
                visited.add(v_node.data);
                Node next_node = v_node.next;
                while (next_node != null && visited.contains(next_node.data)) {
                    next_node = next_node.next;
                }
                v_node = next_node;
            }
            if (v_node != null)
                stack.push(v_node);
            else
                v_node = stack.popTop();
        }
    }

    /**
     * 广度优先
     * 邻接表 O(N + E)
     * 邻接矩阵 O(N^2)
     */
    public void BFS(){

    }

    private void visit(Node node){
        pn(node.data + " ");
    }

}
