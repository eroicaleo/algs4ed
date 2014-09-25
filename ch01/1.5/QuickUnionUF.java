public class QuickUnionUF {
    private int[] id;
    QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    private int root(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }
    void union(int p, int q) {
        id[root(p)] = root(q);
    }
    boolean connected(int p, int q) {
        return (root(p) == root(q));
    }
    // find the component identifer for p
    int find(int p) {
        return 0;
    }
    // number of connected components
    int count() {
        return 1;
    }
    public static void main(String[] args) {
        StdOut.println("Hello UF!");
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
