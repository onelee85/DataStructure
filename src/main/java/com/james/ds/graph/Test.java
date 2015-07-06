package com.james.ds.graph;


import static com.james.ds.Utils.pln;

public class Test {

	public static void main(String[] args) {
        Graph g = new Graph(5);
        g.insert(0, 1);
        g.insert(1, 2);
        g.insert(1, 3);
        g.insert(2, 3);
        g.insert(3, 4);
        g.print();

        g.insertGraphLink(0, 1);
        g.insertGraphLink(1, 2);
        g.insertGraphLink(1, 3);
        g.insertGraphLink(2, 3);
        g.insertGraphLink(3, 4);
        g.printGraphLink();
	}
}
