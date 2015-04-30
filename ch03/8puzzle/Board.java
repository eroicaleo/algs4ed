public class Board {
    private final int N;
    private final int[][] tiles;
    private final int hammingDistance;
    private final int manhattanDistance;

    // construct a board from an N-by-N array of blocks 
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        tiles = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                tiles[i][j] = blocks[i][j];
        hammingDistance = hammingInternal();
        manhattanDistance = manhattanInternal();
    }
    // board dimension N
    public int dimension() {                 
        return N;
    }
    private boolean isOnTarget(int k) {
        assert (k > 0) && (k < N*N);
        int i = (k-1)/N;
        int j = k-i*N-1;
        return (tiles[i][j] == k);
    }
    public int hamming() {
        return hammingDistance;
    }
    // number of blocks out of place
    private int hammingInternal() {                   
        // for (int i = 0; i < N*N-1; i++) {
        //     System.out.format("%d ", i+1);
        // }
        // System.out.format("\n-----------------\n");
        int d = 0;
        for (int i = 1; i < N*N; i++) {
            if (isOnTarget(i)) {
                // System.out.format("%d ", 0);
            }
            else {
                d++;
                // System.out.format("%d ", 1);
            }
        }
        // System.out.format("\nHamming distance is %d.\n", d);
        return d;
    }
    private int manhattan(int i, int j) {
        int k = tiles[i][j];
        if (k == 0) return 0;
        int ii = (k-1)/N;
        int jj = k-ii*N-1;
        return Math.abs(ii - i) + Math.abs(jj - j);
    }
    public int manhattan() {
        return manhattanDistance;
    }
    // sum of Manhattan distances between blocks and goal
    private int manhattanInternal() {                 
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         if (tiles[i][j] == 0) continue;
        //         System.out.format("%d ", tiles[i][j]);
        //     }
        // }
        // System.out.format("\n-----------------\n");
        int d = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) continue;
                int md = manhattan(i, j);
                // System.out.format("%d ", md);
                d += md;
            }
        }
        // System.out.format("\nManhattan distance is %d.\n", d);
        return d;
    }
    // is this board the goal board?
    public boolean isGoal() {                
        return (hamming() == 0);
    }
    // a board that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {                    
        int row = 0;
        for (int i = 0; i < N; i++) {
            if (tiles[i][0] != 0 && tiles[i][1] != 0) {
                row = i;
                break;
            }
        }

        int[][] nTiles = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                nTiles[i][j] = tiles[i][j];

        int tmp = nTiles[row][0];
        nTiles[row][0] = nTiles[row][1];
        nTiles[row][1] = tmp;
        return new Board(nTiles);
    }
    // does this board equal y?
    public boolean equals(Object y) {        
        if (this == y) return true;
        if (y == null) return false;

        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;

        if (this.N != that.N) return false;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (this.tiles[i][j] != that.tiles[i][j]) return false;

        return true;
    }
    // all neighboring boards
    public Iterable<Board> neighbors() {     
        Queue<Board> queue = new Queue<Board>();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (this.tiles[i][j] == 0) {
                   if (i > 0) {
                       /* left neighbor */
                       Board c = new Board(tiles);
                       c.tiles[i][j] = tiles[i-1][j];
                       c.tiles[i-1][j] = 0;
                       queue.enqueue(new Board(c.tiles));
                   }
                   if (i < N-1) {
                       /* right neighbor */
                       Board c = new Board(tiles);
                       c.tiles[i][j] = tiles[i+1][j];
                       c.tiles[i+1][j] = 0;
                       queue.enqueue(new Board(c.tiles));
                   }
                   if (j > 0) {
                       /* up neighbor */
                       Board c = new Board(tiles);
                       c.tiles[i][j] = tiles[i][j-1];
                       c.tiles[i][j-1] = 0;
                       queue.enqueue(new Board(c.tiles));
                   }
                   if (j < N-1) {
                       /* up neighbor */
                       Board c = new Board(tiles);
                       c.tiles[i][j] = tiles[i][j+1];
                       c.tiles[i][j+1] = 0;
                       queue.enqueue(new Board(c.tiles));
                   }
                }
        return queue;
    }
    // string representation of this board (in the output format specified below)
    public String toString() {               
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
    // unit tests (not graded)
    public static void main(String[] args) { 
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
        System.out.format("Manhattan distance is %d!\n", initial.manhattan());
        /* Test isGoal() */
        if (initial.isGoal())
            System.out.format("Is it the goal!\n");
        else
            System.out.format("It isn't the goal!\n");
        /* Test twin() */
        System.out.format("It's twin %s\n", initial.twin());
    }
}
