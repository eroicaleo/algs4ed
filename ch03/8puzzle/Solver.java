import java.util.Comparator;

public class Solver {
    /*****************************
     * The Comparator
     * ***************************/
    public static final ByManhattan BY_MANHATTAN = new ByManhattan();
    private static class ByManhattan implements Comparator<Node> {
        public int compare(Node v, Node w) {
            int cmp = v.manPriority - w.manPriority;
            if (cmp != 0) return cmp;
            cmp = v.b.manhattan() - w.b.manhattan();
            return cmp;
        }
    }

    /*****************************
     * Search Node
     * ***************************/
    private class Node {
        private final Board b;
        private final Node  prev;
        private final int   step;
        private final int   hamPriority;
        private final int   manPriority;

        public Node(Board b, Node p, int s) {
            this.b    = b;
            this.prev = p;
            this.step = s;
            this.hamPriority = this.b.hamming() + this.step;
            this.manPriority = this.b.manhattan() + this.step;
        }
    }
    
    /*****************************
     * Main data structure
     * ***************************/
    private MinPQ<Node> minPQ;
    private MinPQ<Node> minPQTwin;
    private Stack<Board> solution;
    private final boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {           
        minPQ     = new MinPQ<Node>(BY_MANHATTAN);
        minPQTwin = new MinPQ<Node>(BY_MANHATTAN);
        solution  = new Stack<Board>();
        
        Node x = new Node(initial, null, 0);
        Node y = new Node(initial.twin(), null, 0);
        System.out.format("%s", x.b);
        while (!x.b.isGoal() && !y.b.isGoal()) {
            for (Board b : x.b.neighbors()) {
                Node t = new Node(b, x, x.step+1);
                /* Critical optimization: check if is searched */
                if ((x.prev == null) || (!b.equals(x.prev.b))) {
                    minPQ.insert(t);
                } 
            }
            x = minPQ.delMin();
            // System.out.format
            // ("### min node: %s, manhattan %d\n", x.b, x.manPriority);

            for (Board b : y.b.neighbors()) {
                Node t = new Node(b, y, y.step+1);
                /* Critical optimization: check if is searched */
                if ((y.prev == null) || (!b.equals(y.prev.b))) {
                    minPQTwin.insert(t);
                } 
            }
            y = minPQTwin.delMin();
            // System.out.format
            // ("### min node: %s, manhattan %d\n", y.b, y.manPriority);
        }

        if (x.b.isGoal()) {
            isSolvable = true;
            while (x.prev != null) {
                solution.push(x.b);
                x = x.prev;
            }
        } else {
            isSolvable = false;
        }
    }
    // is the initial board solvable?
    public boolean isSolvable() {            
        return isSolvable;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {                     
        if (isSolvable()) return solution.size();
        return -1;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {      
        Queue<Board> queue = new Queue<Board>();
        for (Board b : solution) {
            queue.enqueue(b);
        }
        return queue;
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args) { 
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
