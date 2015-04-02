import java.util.Comparator;

public class ShellSortComparator {
    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }
    private static void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void sort(Object[] a, Comparator c) {
        int N = a.length;
        int k = 1;
        // Knuth sequence
        while (k < N) {
            k = 3*k+1;
        }
        k /= 3;

        while (k > 0) {
            for (int i = k; i < N; i++)
                for (int j = i; j >= k && less(c, a[j], a[j-k]); j -= k)
                     swap(a, j, j-k);
            k /= 3;
        }
    }
}
