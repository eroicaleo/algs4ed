import java.util.Comparator;

public class MergeSortBUComparator {
    private static Object[] aux;
    private static boolean less(Comparator c, Object a, Object b) {
        return (c.compare(a, b) < 0);
    }
    private static void merge(Object[] a, int lo, int mid, int hi, Comparator c) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                      a[k] = aux[j++];
            else if (j > hi)                  a[k] = aux[i++];
            else if (less(c, aux[j], aux[i])) a[k] = aux[j++];
            else                              a[k] = aux[i++];
        }
    }
    public static void sort(Object[] a, Comparator c) {
        int N = a.length;
        aux = new Object[N];
        for (int sz = 1; sz < N; sz = sz+sz) {
            for (int lo = 0; lo < N; lo += sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(N-1, lo+sz+sz-1), c);
            }
        }
    }
}
