import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] a;
    private int sz;
    private int head, tail;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        a = (Item []) new Object[2];
        sz = 0;
        head = 0;
        tail = 0;
    }
    public boolean isEmpty()                 // is the queue empty?
    {
        return sz == 0;
    }
    public int size()                        // return the number of items on the queue
    {
        return sz;
    }
    public void enqueue(Item item)           // add the item
    {
        if (sz == a.length - 1) resize(2 * a.length);
        a[tail] = item;
        sz++;
        moveTail();
    }
    private void resize(int capacity) {
        Item[] copy = (Item []) new Object[capacity];
        for (int i = 0; i < sz; i++) {
            copy[i] = a[(head+i)%a.length];
        }
        // StdOut.println("Before resizeing: head = " + head + ", tail = " + tail + ", sz = " + sz + ".");
        // StdOut.println("Before resizeing: copy.length = " + copy.length + ", copy[0] = " + copy[0] + " a[head] = " + a[head] + ", sz = " + sz + ".");
        a = copy;
        head = 0;
        tail = sz;
        StdOut.println("After resizeing: head = " + head + ", tail = " + tail + ", sz = " + sz + ".");
        StdOut.println("After resizeing: a.length = " + a.length + ", a[head] = " + a[head] + ", sz = " + sz + ".");
    }
    private void moveTail() {
        tail = (++tail) % a.length;
    }
    private void moveHead() {
        head = (++head) % a.length;
    }
    public Item dequeue()                    // delete and return a random item
    {
        Item item = a[head];
        a[head] = null;
        moveHead();
        sz--;
        if (sz > 0 && sz == a.length/4) resize(a.length/2);
        return item;
    }
    public Item sample()                     // return (but do not delete) a random item
    {
        return a[head];
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new randomQueueIterator();
    }
    private class randomQueueIterator implements Iterator<Item> {
        int current;
        public randomQueueIterator() {
            current = head;
        }
        public void remove() { throw new UnsupportedOperationException(); }
        public boolean hasNext() { return current != tail; }
        public Item next() {
            if (current == tail) { throw new java.util.NoSuchElementException("from next() of randomQueueIterator!"); }
            Item item = a[current];
            current = (++current) % a.length;
            return item;
        }
    }
    public static void main(String[] args)   // unit testing
    {
    }
}
