public class Sort {
    private static boolean Less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    private static void Exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (Less(a[i], a[i-1])) return false;
        return true;
    }
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (Less(a[j], a[i]))
                    Exch(a, i, j);
            }
        }
    }
}
