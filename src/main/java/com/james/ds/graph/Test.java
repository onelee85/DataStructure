package com.james.ds.graph;


import static com.james.ds.Utils.pln;

public class Test {

	public static void main(String[] args) {
        Graph g = new Graph(7);
        g.insert(1, 2);
        g.insert(1, 3);
        g.insert(2, 3);
        g.insert(2, 4);
        g.insert(3, 5);
        g.insert(5, 6);
        g.print();

        g.insertGraphLink(1, 2);
        g.insertGraphLink(1, 3);
        g.insertGraphLink(1, 5);
        g.insertGraphLink(2, 5);
        g.insertGraphLink(3, 6);
        g.insertGraphLink(5, 4);
        g.insertGraphLink(6, 4);
        g.printGraphLink();
        pln();

        g.DFS(1);
	}
}
