import edu.princeton.cs.algs4.StdRandom;

public class MyHeap {

    private MyHeap() { }

    public static void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--)
            sink(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
        assert isSorted(pq);
    }

    private static void sink(Comparable[] pq, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }

    public static void show(Comparable[] a) {
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
        MyHeap.show(a);
        System.out.println("##### Heap Sort #####");
        MyHeap.sort(a);
        MyHeap.show(a);

    }
}
