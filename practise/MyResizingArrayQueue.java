import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int first;
    private int last;

    public MyResizingArrayQueue() {
        q = (Item[]) new Object[2];
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
        assert n >= capacity;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public void enqueue(Item item) {
        if (n == q.length) resize(2 * q.length);
        q[last++] = item;
        if (last == q.length) {
            last = 0;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        if (first == q.length) {
            first = 0;
        }
        if (n > 0 && n == q.length / 4) resize(q.length/2);
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[first];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i;
        public ArrayIterator() {
            i = 0;
        }
        public boolean hasNext() {
            return i < n;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            if (i == q.length) i = 0;
            return item;
        }
    }

    public static void main(String[] args) {
        File file = new File("StackQueueInput.txt");
        MyResizingArrayQueue<String> queue = new MyResizingArrayQueue<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String item = scanner.next();
                if (!item.equals("-")) {
                    queue.enqueue(item);
                } else {
                    System.out.printf("%s ", queue.dequeue());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
