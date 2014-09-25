public class UF {
    private int[] id;
    UF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    void union(int p, int q) {
        return;
    }
    boolean connected(int p, int q) {
        return true;
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
        UF uf = new UF(N);
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
