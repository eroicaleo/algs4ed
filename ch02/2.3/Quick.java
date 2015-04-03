public class Quick {
    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swp = a[i];
        a[i] = a[j];
        a[j] = swp;
    }
    private static int partition(Comparable[] a, int lo, int hi) {

        int i = lo, j = hi+1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;

            while (less(a[lo], a[--j]))
                if (j == lo) break;

            // = is possible when a[i] = a[j] = a[lo]
            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);

        int lo = 0, hi = a.length-1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j > k)      hi = j - 1;
            else if (j < k) lo = j + 1;
            else            return a[k];
        }
        return a[k];
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        Double ak = (Double)Quick.select(a, 3);
        System.out.format("The 3th element in a is: %f\n", ak);
        Quick.sort(a);
        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
    }
}
