import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yangge on 2/5/2016.
 */
public class MergeSort {

    private MergeSort() {}

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        isSorted(a, lo, mid);
        isSorted(a, mid+1, hi);

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

        isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int k = lo; k < hi; k++) {
            if (less(a[k+1], a[k]))
                return false;
        }
        return true;
    }

    private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        StdRandom.setSeed(20121228);

        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(100);
        }
        MergeSort.show(a);
        System.out.println("##### MergeSort #####");
        MergeSort.sort(a);
        MergeSort.show(a);

    }
}
