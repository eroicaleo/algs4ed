public class Shuffling {
    private static void exch(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void shuffle(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            int r = StdRandom.uniform(i+1);
            exch(a, i, r);
        }
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
        }
        Shuffling.shuffle(a);
        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
    }
}
