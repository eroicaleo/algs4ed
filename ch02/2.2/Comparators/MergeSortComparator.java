import java.util.Comparator;

public class MergeSortComparator {
    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }
    private static void merge(Object[] a, Object[] aux, int lo, int mid, int hi, Comparator c) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                  a[k] = aux[j++];
            else if (j > hi)              a[k] = aux[i++];
            else if (less(c, aux[j], aux[i])) a[k] = aux[j++];
            else                          a[k] = aux[i++];
        }
    }
    private static void sort(Object[] a, Object[] aux, int lo, int hi, Comparator c) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, c);
        sort(a, aux, mid+1, hi, c);
        merge(a, aux, lo, mid, hi, c);
    }
    public static void sort(Object[] a, Comparator c) {
        int N = a.length;
        Object[] aux = new Object[N];
        for (int i = 0; i < N; i++)
            aux[i] = a[i];

        sort(a, aux, 0, a.length-1, c);
    }
    public static void main(String[] args) {
        return;
    }
}
