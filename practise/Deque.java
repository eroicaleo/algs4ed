import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("addFirst item is null");
        Node node = new Node();
        node.item = item;
        node.next = first;
        node.prev = null;
        if (first != null) first.prev = node;
        first = node;
        n++;
        if (n == 1)
            last = first;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("addLast item is null");
        Node node = new Node();
        node.item = item;
        node.prev = last;
        node.next = null;
        if (last != null) last.next = node;
        last = node;
        n++;
        if (n == 1)
            first = last;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow!");
        Node node = first;
        first = first.next;
        n--;
        if (first == null) last = first;
        else               first.prev = null;
        return node.item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow!");
        Node node = last;
        last = last.prev;
        n--;
        if (last == null) first = last;
        else              last.next = null;
        return node.item;
    }

    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        for (int i : deque) {
            System.out.println(i + " ");
        }
        System.out.println("size: " + deque.size());
        deque.removeLast();
        for (int i : deque) {
            System.out.println(i + " ");
        }
        System.out.println("size: " + deque.size());
        deque.removeFirst();
        for (int i : deque) {
            System.out.println(i + " ");
        }
        System.out.println("size: " + deque.size());
        deque.removeFirst();
        for (int i : deque) {
            System.out.println(i + " ");
        }
        System.out.println("size: " + deque.size());
        System.out.println("isEmpty: " + deque.isEmpty());
    }
}
