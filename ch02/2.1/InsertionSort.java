public class InsertionSort {
    private void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            for (int j = i-1; j >= 0; j--)
                if (less(a[j], a[j+1]))
                    exch(a[j], a[j+1]);
    }
    public static void main(String[] argv) {
    }
}
