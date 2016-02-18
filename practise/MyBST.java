import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by yangge on 2/16/2016.
 */
public class MyBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N   = N;
        }
    }

    public MyBST() { }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left,  key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        assert check();
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else           return x.N;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    public Key floor(Key key) {
        if (isEmpty()) throw new NoSuchElementException("Called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else           return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    public Key ceiling(Key key) {
        if (isEmpty()) throw new NoSuchElementException("Called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else           return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else           return x;
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else            return x;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else              return size(x.left);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)                keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0)                keys(x.right, queue, lo, hi);
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        else           return Math.max(height(x.left), height(x.right)) + 1;
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<>();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && min.compareTo(x.key) >= 0) return false;
        if (max != null && max.compareTo(x.key) <= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    public boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key: keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    public boolean check() {
        if (!isBST())            System.out.println("Not in symmetric order");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) System.out.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left  == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left  = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args) {
        StdRandom.setSeed(20121228);
        int N = 10;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(100);

        ArrayList<Person> p = new ArrayList<>();
        In in = new In("Characters.txt");
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] names  = line.split(" ");
            p.add(new Person(names[0], names[1]));
        }

        System.out.println("\n##### Test size/isEmpty/put #####");
        MyBST<Person, Integer> myBST = new MyBST<>();
        System.out.printf("myBST size: %d\n", myBST.size());
        System.out.println("Is myBST empty? " + myBST.isEmpty());

        myBST.put(p.get(0), a[0]);
        System.out.printf("myBST size: %d\n", myBST.size());
        System.out.println("Is myBST empty? " + myBST.isEmpty());

        for (int i = 1; i < p.size(); i++)
            myBST.put(p.get(i), a[i]);
        System.out.printf("myBST size: %d\n", myBST.size());
        System.out.println("Is myBST empty? " + myBST.isEmpty());

        System.out.println("\n##### Test get #####");
        System.out.println("Peppa pig id: " + myBST.get(new Person("Peppa", "Pig")));
        System.out.println("a[0] = " + a[0]);
        System.out.println("\n##### Test contains #####");
        System.out.println("Contains Suze Sheep: " + myBST.contains(new Person("Suzy", "Sheep")));
        System.out.println("Contains Emily Elephant: " + myBST.contains(new Person("Emily", "Elephant")));

        System.out.println("\n##### Test min/max #####");
        System.out.println("myBST.min() = " + myBST.min());
        System.out.println("myBST.max() = " + myBST.max());

        System.out.println("\n##### Test floor/ceiling #####");
        System.out.println("Floor of George Pig: " + myBST.floor(new Person("George", "Pig")));
        System.out.println("Floor of Amy Cat: " + myBST.floor(new Person("Amy", "Cat")));
        System.out.println("Ceiling of Uncle Pig: " + myBST.ceiling(new Person("Uncle", "Pig")));
        System.out.println("Ceiling of Zoey Zebra: " + myBST.ceiling(new Person("Zoey", "Zebra")));

        System.out.println("\n##### Test select #####");
        for (int i = 0; i < myBST.size(); i++)
            System.out.printf("Select %d, %s\n", i, myBST.select(i));

        System.out.println("\n##### Test rank #####");
        for (Person ps : p) {
            System.out.printf("%s is rank %d\n", ps, myBST.rank(ps));
        }

        System.out.println("\n##### Test keys and size(lo, hi) #####");
        for (Person ps: myBST.keys()) {
            System.out.printf("Person: %s, id: %d\n", ps, myBST.get(ps));
        }
        System.out.println("The following are between Dinky Dog and Zachary Zebra:");
        for (Person ps: myBST.keys(new Person("Dinky", "Dog"), new Person("Zachary", "Zebra"))) {
            System.out.printf("Person: %s, id: %d\n", ps, myBST.get(ps));
        }
        System.out.printf("The size is: %d\n", myBST.size(new Person("Dinky", "Dog"), new Person("Zachary", "Zebra")));
        System.out.printf("The size between Dinky Dog and Zoe Zebra: %d\n", myBST.size(new Person("Dinky", "Dog"), new Person("Zoe", "Zebra")));

        System.out.println("\n##### Test height #####");
        System.out.printf("The height of the tree is: %d\n", myBST.height());

        System.out.println("\n##### Test levelOrder #####");
        for (Person ps: myBST.levelOrder()) {
            System.out.printf("Person: %s, id: %d\n", ps, myBST.get(ps));
        }

        System.out.println("\n##### Test isBST #####");
        System.out.println("Is the myBST a BST? " + myBST.isBST());

        System.out.println("\n##### Test isSizeConsistent #####");
        System.out.println("Is the myBST size consistent? " + myBST.isSizeConsistent());

        System.out.println("\n##### Test isRankConsistent #####");
        System.out.println("Is the myBST rank consistent? " + myBST.isRankConsistent());

        System.out.println("\n##### Test check() #####");
        System.out.println("Is the myBST passing check()? " + myBST.check());

        System.out.println("\n##### Test deleteMin() #####");
        Person min = myBST.min();
        int id = myBST.get(min);
        myBST.deleteMin();
        for (Person ps: myBST.keys())
            System.out.printf("Person: %s, id: %d\n", ps, myBST.get(ps));
        System.out.println("##### Put the Candy Cat back #####");
        myBST.put(min, id);
        for (Person ps: myBST.keys())
            System.out.printf("Person: %s, id: %d\n", ps, myBST.get(ps));

        System.out.println("\n##### Test deleteMax() #####");
        Person max = myBST.max();
        id = myBST.get(max);
        myBST.deleteMax();
        for (Person ps: myBST.keys())
            System.out.printf("Person: %s, id: %d\n", ps, myBST.get(ps));
        System.out.printf("##### Put the %s back #####\n", max);
        myBST.put(max, id);
        for (Person ps: myBST.keys())
            System.out.printf("Person: %s, id: %d\n", ps, myBST.get(ps));

        System.out.println("\n##### Test delete() #####");
        for (int i = 0; i < p.size(); i++) {
            Person ps = p.get(i);
            id = myBST.get(ps);
            System.out.printf("## Delete person: %s ##\n", ps);
            myBST.delete(ps);
            for (Person ps1: myBST.keys())
                System.out.printf("Person: %s, id: %d\n", ps1, myBST.get(ps1));
            System.out.printf("## Put back person: %s ##\n", ps);
            myBST.put(ps, id);
            for (Person ps1: myBST.keys())
                System.out.printf("Person: %s, id: %d\n", ps1, myBST.get(ps1));
        }

    }

    private static class Person implements Comparable<Person> {
        public String firstName;
        public String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public int compareTo(Person o) {
            if (this.lastName.compareTo(o.lastName) == 0) return this.firstName.compareTo(o.firstName);
            else                                          return this.lastName.compareTo(o.lastName);
        }

        @Override
        public String toString() {
            return new String(firstName + " " + lastName);
        }
    }
}
