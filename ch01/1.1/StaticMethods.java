public class StaticMethods {
    // Newton method
    public static double sqrt(double c) {
        if (c < 0)
            return Double.NaN;
        double e = 1e-15;
        double t = c;
        while (Math.abs(t - c/t) > e*t) {
            t = (t+c/t) / 2;
        }
        return t;
    }
    // Primality test
    public static boolean isPrime(int c) {
        if (c < 2) return false;
        for (int i = 2; i*i <= c; ++i) {
            if (c % i == 0)
                return false;
        }
        return true;
    }
    // Harmonic number
    public static double H(int N) {
        double sum = 0.0;
        for (int i = 1; i <= N; ++i) {
            sum += 1.0/i;
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(sqrt(2.0));
        System.out.println(sqrt(100.0));
        System.out.println(sqrt(-100.0));
        System.out.println(isPrime(4));
        System.out.println(isPrime(17));
        System.out.println(H(17));
    }
}
