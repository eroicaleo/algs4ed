import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yangge on 2/6/2016.
 */
public class MergeSortBU {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int n = 1; n < N; n = n + n) {
            for (int i = 0; i < N - n; i += n + n) {
                int lo  = i;
                int mid = i + n - 1;
                int hi  = Math.min(i+n+n-1, N-1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StdRandom.setSeed(20121228);

        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(100);
        }
        MergeSortBU.show(a);
        MergeSortBU.sort(a);
        System.out.println("##### MergeSortBU #####");
        MergeSortBU.show(a);

    }
}
