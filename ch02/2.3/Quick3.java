import java.util.Random;

public class Quick3 {
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swp = a[i];
        a[i] = a[j];
        a[j] = swp;
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        Comparable v = a[lo];
        int i = lo, lt = lo, gt = hi;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else         i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Random rgen = new Random();

        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = rgen.nextInt(5);
        }
        Quick3.sort(a);
        for (int i = 0; i < N; i++) {
            System.out.format("%d ", a[i]);
        }
        System.out.println("");
    }
}
