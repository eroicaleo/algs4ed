import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;
    private int first;
    private int last;

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[(first+i) % a.length];
        }
        a = temp;
        first = 0;
        last = n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("equeue gets null argument");
        if (n == a.length) resize(2*a.length);
        a[last++] = item;
        if (last == a.length) {
            last = 0;
        }
        n++;
    }

    private void exch(int i, int j) {
        Item swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("dequeue underflows");
        int i = StdRandom.uniform(n);
        exch((first+i) % a.length, first);
        Item item = a[first];
        a[first] = null;
        n--;
        first++;
        if (first == a.length) {
            first = 0;
        }
        if (n > 0 && n == a.length / 4) resize(a.length/2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("sample underflows");
        int i = StdRandom.uniform(n);
        Item item = a[(first+i) % a.length];
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] copy;
        private int i;

        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[n];
            for (int k = 0; k < n; k++) {
                copy[k] = a[(first+k) % a.length];
            }
            StdRandom.shuffle(copy);
            i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[i++];
        }
    }

}
