public class PercolationStats {
    private double[] openNum;
    private int gridSize;
    private int numofTrials;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0)
            throw new IllegalArgumentException("grid size must be >= 1!");
        if (T <= 0)
            throw new IllegalArgumentException("Trial times must be >= 1!");

        gridSize = N;
        numofTrials = T;
        openNum = new double[numofTrials];

        for (int i = 0; i < numofTrials; i++) {
            // System.out.printf("I am in %d trial\n", i);
            Percolation perc = new Percolation(N);

            // open site randomly, until it percolates
            for (int j = 1; j < gridSize*gridSize+1; j++) {
                // System.out.printf("I am opening %d site\n", j);
                int x;
                int y;
                do {
                    x = 1 + StdRandom.uniform(gridSize);
                    y = 1 + StdRandom.uniform(gridSize);
                } while (perc.isOpen(x, y));
                perc.open(x, y);
                if (perc.percolates()) {
                    openNum[i] = (double) j / (gridSize*gridSize);
                    break;
                }
            }
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        double sum = 0.0;
        for (int i = 0; i < openNum.length; i++) {
            sum += openNum[i];
        }
        return (sum/openNum.length);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        double sum = 0.0;
        double mean = mean();
        for (int i = 0; i < openNum.length; i++) {
            sum += (openNum[i] - mean) * (openNum[i] - mean);
        }
        return Math.sqrt(sum/(openNum.length-1));
    }
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(openNum.length);
    }
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(openNum.length);
    }
    // test client, described below
    public static void main(String[] args) {
        StdRandom.setSeed(20121228);
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        System.out.printf("N = %d, T = %d\n", N, T);
        PercolationStats percStat = new PercolationStats(N, T);
        System.out.printf("%-30s = %f\n", "mean", percStat.mean());
        System.out.printf("%-30s = %f\n", "stddev", percStat.stddev());
        System.out.printf("%-30s = %f, %f\n", "95% confidence interval",
                percStat.confidenceLo(), percStat.confidenceHi());
        return;
    }
}
