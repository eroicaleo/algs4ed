public class Board {
    private final int N;
    private final int[][] tiles;

    public Board(int[][] blocks) {           // construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
        N = blocks.length;
        tiles = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                tiles[i][j] = blocks[i][j];
    }
    public int dimension() {                 // board dimension N
        return N;
    }
    private boolean isOnTarget(int k) {
        assert (k > 0) && (k < N*N);
        int i = (k-1)/N;
        int j = k-i*N-1;
        return (tiles[i][j] == k);
    }
    public int hamming() {                   // number of blocks out of place
        for (int i = 0; i < N*N-1; i++) {
            System.out.format("%d ", i+1);
        }
        System.out.format("\n-----------------\n");
        int d = 0;
        for (int i = 1; i < N*N; i++) {
            if (isOnTarget(i)) {
                System.out.format("%d ", 0);
            }
            else {
                d++;
                System.out.format("%d ", 1);
            }
        }
        System.out.format("\nHamming distance is %d.\n", d);
        return d;
    }
    private int manhattan(int i, int j) {
        int k = tiles[i][j];
        if (k == 0) return 0;
        int ii = (k-1)/N;
        int jj = k-ii*N-1;
        // System.out.format("i = %d, j = %d, k = %d, ii = %d, jj = %d\n", i, j, k, ii, jj);
        return Math.abs(ii - i) + Math.abs(jj - j);
    }
    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) continue;
                System.out.format("%d ", tiles[i][j]);
            }
        }
        System.out.format("\n-----------------\n");
        int d = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) continue;
                int md = manhattan(i, j);
                System.out.format("%d ", md);
                d += md;
            }
        }
        System.out.format("\nManhattan distance is %d.\n", d);
        return d;
    }
    public boolean isGoal() {                // is this board the goal board?
        return (hamming() == 0);
    }
    public Board twin() {                    // a board that is obtained by exchanging two adjacent blocks in the same row
        return this;
    }
    public boolean equals(Object y) {        // does this board equal y?
        return false;
    }
    public Iterable<Board> neighbors() {     // all neighboring boards
        Queue<Board> queue = new Queue<Board>();
        return queue;
    }
    public String toString() {               // string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    public static void main(String[] args) { // unit tests (not graded)
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        /* Test Constructor and toString */
        System.out.format("%s\n", initial);
        /* Test dimension() */
        System.out.format("It's dimension is %d.\n", initial.dimension());
        /* Test hamming() */
        initial.hamming();
        /* Test manhattan() */
        initial.manhattan();
        /* Test isGoal() */
        if (initial.isGoal())
            System.out.format("Is it the goal!\n");
        else
            System.out.format("It isn't the goal!\n");
    }
}
