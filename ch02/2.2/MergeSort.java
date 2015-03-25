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
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid+1, hi);
        merge(aux, a, lo, mid, hi);
        return;
    }
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];
        sort(a, aux, 0, a.length-1);
    }
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                aux[k] = a[j++];
            else if (j > hi)
                aux[k] = a[i++];
            else if (less(a[j], a[i]))
                aux[k] = a[j++];
            else
                aux[k] = a[i++];
        }

        assert isSorted(a, lo, hi);
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        MergeSort.sort(a);
        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
    }
}
