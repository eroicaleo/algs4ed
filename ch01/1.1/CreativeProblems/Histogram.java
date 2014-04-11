public class Histogram {
    public static int Rank(double[] arr, double d) {
        int N = arr.length;
        int lo = 0;
        int hi = N - 1;
        if (d < arr[lo] || d > arr[hi]) {
            return -1;
        }
        while (hi - lo > 1) {
            int mid = (hi + lo) / 2;
            if (d < arr[mid]) hi = mid;
            else if (d > arr[mid]) lo = mid;
            else return mid;
        }
        return lo;
    }
    public static void DrawHistogram() {
        StdDraw.setXscale(-20.0, 20.0);
        StdDraw.setYscale(0.0, 10);
        int N = 10;
        double l = 0.0;
        double s = 10.0;
        int K = 10;
        double[] arr = new double[N+1];
        int[] histo = new int[N];
        for (int i = 0; i < N+1; ++i) {
            arr[i] = l + (s - l) / N * i;
        }
        for (int i = 0; i < K; i++) {
            double d = StdRandom.uniform(l, s);
            int rank = Rank(arr, d);
            if (rank < 0 || rank >= N) {
                StdOut.printf("rank = %d, i = %d.\n", rank, i);
            } else {
                ++histo[rank];
                StdOut.printf("rank = %d, d = %.2f.\n", rank, d);
            }
        }
        for (int i = 0; i < N; ++i) {
            double x = (arr[i] + arr[i+1])/2;
            double y = histo[i] / 2.0;
            StdDraw.filledRectangle(x, y, (s-l)/(4*N), y);
        }
    }
    public static void main(String[] args) {
        DrawHistogram();
    }
}
