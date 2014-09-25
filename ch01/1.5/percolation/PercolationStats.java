public class PercolationStats {
    private int[] openNum;
    private int gridSize;
    private int numofTrials;

    public PercolationStats(int N, int T) {    // perform T independent computational experiments on an N-by-N grid
        if (N <= 0) throw new IllegalArgumentException("grid size must be >= 1!");
        if (T <= 0) throw new IllegalArgumentException("Trial times must be >= 1!");
    }
    public double mean() {                     // sample mean of percolation threshold
        return 0.0;
    }
    public double stddev() {                   // sample standard deviation of percolation threshold
        return 0.0;
    }
    public double confidenceLo() {             // returns lower bound of the 95% confidence interval
        return 0.0;
    }
    public double confidenceHi() {             // returns upper bound of the 95% confidence interval
        return 0.0;
    }
    public static void main(String[] args) {   // test client, described below
        return;
    }
}
