import java.util.Random;

public class HeapSort {
    private HeapSort {
    }
    private static boolean less(Comparable[] a, int i, int j) {
        return (a[i-1].compareTo(a[j-1]) < 0);
    }
    private static void swap(Comparable[] a, int i, int j) {
        Comparable swap = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = swap;
    }
    private static void sink(Comparable[] a, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j+1 <= N && less(a, j, j+1)) j++;
            if (!less(a, k, j)) break;
            swap(a, k, j);
            k = j;
        }
    }
    private static void heapify(Comparable[] a) {
        for (int k = (a.length-1)/2; k > 0; k--)
            sink(a, k, a.length-1);
    }
    private static void heapifysort(Comparable[] a) {
        for (int k = a.length-1; k > 0; k--) {
            swap(a, 1, k);
            sink(a, 1, k-1);
        }
    }
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }

        while (N > 1) {
            swap(a, 1, N--);
            sink(a, 1, N);
        }
    }
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.format("%d ", a[i]);
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Integer[] a = new Integer[N];
        Random rgen = new Random();
        for (int i = 0; i < N; i++) {
            a[i] = rgen.nextInt(5);
        }
        show(a);
        HeapSort.sort(a);
        show(a);
    }
}
