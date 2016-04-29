import edu.princeton.cs.algs4.*;

/**
 * Created by yangge on 4/26/2016.
 */
public class MyBreadthFirstPaths {
    public static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public MyBreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);

        assert check(G, s);
    }

    public void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo(x) != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

    public boolean check(Graph G, int s) {
        if (distTo[s] != 0) {
            System.out.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }

                if (hasPathTo(v) && (distTo(w) > distTo(v) + 1)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("distTo(" + v + ") = " + distTo(v));
                    System.out.println("distTo(" + w + ") = " + distTo(w));
                    return false;
                }
            }
        }

        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo(w) != distTo(v) + 1) {
                System.out.println("shortest path edge " + v + "-" + w);
                System.out.println("distTo(" + v + ") = " + distTo(v));
                System.out.println("distTo(" + w + ") = " + distTo(w));
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        In in = new In("tinyCG.txt");
        Graph G = new Graph(in);
        int s = 0;
        MyBreadthFirstPaths bfs = new MyBreadthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.printf("\ndistance from %d to 0 is %d.", v, bfs.distTo(v));
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }

    }

}
