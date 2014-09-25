public class PercolationStats {
    private double[] openNum;
    private int gridSize;
    private int numofTrials;

    public PercolationStats(int N, int T) {    // perform T independent computational experiments on an N-by-N grid
        if (N <= 0) throw new IllegalArgumentException("grid size must be >= 1!");
        if (T <= 0) throw new IllegalArgumentException("Trial times must be >= 1!");

        StdRandom.setSeed(20121228);
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
                    openNum[i] = (double)j / (gridSize*gridSize);
                    break;
                }
            }
        }
    }
    public double mean() {                     // sample mean of percolation threshold
        double sum = 0.0;
        for (int i = 0; i < openNum.length; i++) {
            sum += openNum[i];
        }
        return (sum/openNum.length);
    }
    public double stddev() {                   // sample standard deviation of percolation threshold
        double sum = 0.0;
        double mean = mean();
        for (int i = 0; i < openNum.length; i++) {
            sum += (openNum[i] - mean) * (openNum[i] - mean);
        }
        return sum/(openNum.length-1);
    }
    public double confidenceLo() {             // returns lower bound of the 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * Math.sqrt(stddev/openNum.length);
    }
    public double confidenceHi() {             // returns upper bound of the 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * Math.sqrt(stddev/openNum.length);
    }
    public static void main(String[] args) {   // test client, described below
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        System.out.printf("N = %d, T = %d\n", N, T);
        PercolationStats percStat = new PercolationStats(N, T);
        System.out.printf("%-30s = %f\n", "mean", percStat.mean());
        System.out.printf("%-30s = %f\n", "stddev", percStat.stddev());
        System.out.printf("%-30s = %f, %f\n", "95% confidence interval", percStat.confidenceLo(), percStat.confidenceHi());
        return;
    }
}
