package com.company;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by yg943079 on 2/23/17.
 */
public class MyDirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public MyDirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                // Will the order of the following 2 statements cause problem?
                // Yes! Have to set edgeTo[w] first
                // Otherwise won't be travel back
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    private boolean check() {
        if (hasCycle()) {
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1)
                    first = v;
                last = v;
            }
            if (first != last)
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        Digraph G = new Digraph(in);
        MyDirectedCycle finder = new MyDirectedCycle(G);
        System.out.printf("Graph has cycle? %b\n", finder.hasCycle());
        for (int v : finder.cycle()) {
            System.out.printf("%d ", v);
        }
        System.out.printf("\n");
        in.close();
        in = new In("tinyDAG.txt");
        G = new Digraph(in);
        finder = new MyDirectedCycle(G);
        System.out.printf("Graph has cycle? %b\n", finder.hasCycle());
        in.close();
    }
}
