public class MyRandomSeq {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);
        for (int i = 0; i < N; ++i) {
            double d = StdRandom.uniform(lo, hi);
            StdOut.printf("%.5f\n", d);
        }
        StdOut.printf("The approx value of pi is %.2f\n", Math.PI);
    }
}
