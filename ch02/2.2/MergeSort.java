public class MergeSort {
    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        // FIXME
        return true;
    }
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = a[j++];
            else if (j > hi)
                a[k] = a[i++];
            else if (less(a[j], a[i]))
                a[k] = a[j++];
            else
                a[k] = a[i++];
        }

        assert isSorted(a, lo, hi);
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        // MergeSort.sort(a);
        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
    }
}
