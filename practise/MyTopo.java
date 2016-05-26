import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.SymbolDigraph;

import java.util.Stack;

/**
 * Created by yangge on 5/10/2016.
 */
public class MyTopo {
    private boolean[] marked;
    private Stack<Integer> reversePost;

    public MyTopo(Digraph G) {
        marked = new boolean[G.V()];
        reversePost = new Stack<Integer>();
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            for (int v = 0; v < G.V(); v++) {
                if (!marked[v])
                    dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
        reversePost.push(v);
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        // Please download the "jobs.txt" from here first:
        //
        SymbolDigraph sg = new SymbolDigraph("jobs.txt", "/");
        MyTopo myTopo = new MyTopo(sg.G());
        for (int v : myTopo.reversePost()) {
            System.out.println(sg.name(v));
        }
    }

}
