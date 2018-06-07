import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;
    private double[] numberOfOpenSites;

    public PercolationStats(int n, int trials) {
        validate(n, trials);
        numberOfOpenSites = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                perc.open(row, col);
            }
            numberOfOpenSites[i] = (double) perc.numberOfOpenSites() / (double) (n*n);
            // StdOut.printf("trial %d, %d\n", i, perc.numberOfOpenSites());
        }
        mean = StdStats.mean(numberOfOpenSites);
        stddev = StdStats.stddev(numberOfOpenSites);
        double c = 1.96;
        confidenceLo = mean - c * stddev / Math.sqrt(trials);
        confidenceHi = mean + c * stddev / Math.sqrt(trials);
    }

    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n or trials <= 0");
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("%-40s = %f\n", "mean", ps.mean());
        StdOut.printf("%-40s = %f\n", "stddev", ps.stddev());
        StdOut.printf("%-40s = [%f, %f]\n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());

    }
}
