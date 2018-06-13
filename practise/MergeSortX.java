import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yangge on 2/7/2016.
 */
public class MergeSortX {

    private static final int CUT_OFF = 7;

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];
            else                           dst[k] = src[i++];
        }

        assert isSorted(dst, lo, hi);
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j-1, j);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo + CUT_OFF) {
            insertionSort(dst, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi-lo+1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1);
        assert isSorted(a);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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
        MergeSortX.show(a);
        MergeSortX.sort(a);
        System.out.println("##### Sorted MergeSortX #####");
        MergeSortX.show(a);

    }

}
