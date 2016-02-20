import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by yangge on 2/12/2016.
 */
public class MyMaxPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int N;
    private Comparator<Key> comparator;

    public MyMaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity+1];
        N = 0;
    }

    public MyMaxPQ() {
        this(1);
    }

    public MyMaxPQ(int initCapacity, Comparator<Key> comparator) {
        pq = (Key[]) new Object[initCapacity+1];
        N = 0;
        this.comparator = comparator;
    }

    public MyMaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MyMaxPQ(Key[] keys) {
        pq = (Key[]) new Object[keys.length+1];
        N = keys.length;
        for (int i = 0; i < N; i++)
            pq[i+1] = keys[i];
        for (int k = N / 2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }

    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority Queue underflow");
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length/2);
        assert isMaxHeap();
        return max;
    }

    public void insert(Key key) {
        if (N >= pq.length - 1) resize(pq.length*2);
        pq[++N] = key;
        swim(N);
        assert isMaxHeap();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    public boolean isMaxHeap(int k) {
        if (k > N) return true;
        int left = 2 * k, right = 2 * k + 1;
        if (left  <= N && less(k, left))  return false;
        if (right <= N && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority Queue underflow");
        return pq[1];
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k / 2;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    public static void show(Object[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] != null)
                System.out.print(a[i] + " ");
        System.out.println();
    }

    public Key get(int k) {
        if (k < 1 || k > size()) throw new IndexOutOfBoundsException("Selected element out of bounds");
        return pq[k];
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MyMaxPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) copy = new MyMaxPQ<Key>(size());
            else                    copy = new MyMaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        StdRandom.setSeed(20121228);
        MyMaxPQ<Integer> pq = new MyMaxPQ<Integer>();
        int N = 10;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(100);
            pq.insert(a[i]);
            MyMaxPQ.show(a);
            System.out.printf("Max element in priority queue: %d, size: %d\n", pq.max(), pq.size());
            System.out.println("If pq is priority queue: " + pq.isMaxHeap());
        }

        System.out.println("##### Loop with iterator #####");
        for (Integer k: pq) {
            System.out.print(k + " ");
        }
        System.out.println();

        System.out.println("##### Loop with while and delMax #####");
        while (!pq.isEmpty())
            System.out.print(pq.delMax() + " ");
        System.out.println();

        pq = new MyMaxPQ<Integer>(a);
        System.out.println("##### Loop with iterator for new pq #####");
        for (Integer k: pq) {
            System.out.print(k + " ");
        }
        System.out.println();

    }
}
