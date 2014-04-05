import java.util.Arrays;

public class API {
    public static double uniform(double a, double b) {
        return a + StdRandom.random() * (b-a);
    }
    public static void shuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; ++i) {
            int r = i + StdRandom.uniform(N-i);
            double tmp = a[r];
            a[r] = a[i];
            a[i] = tmp;
        }
    }
    public static void main(String[] args) {
        System.out.println(uniform(1.0, 2.0));
        double[] a = {1.0, 2.0, 3.0, 4, 5};
        shuffle(a);
        System.out.println(Arrays.toString(a));
        shuffle(a);
        System.out.println(Arrays.toString(a));
        shuffle(a);
        System.out.println(Arrays.toString(a));
    }
}
