import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;
        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.count = N;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)      x = x.left;
            else if (cmp > 0) x = x.right;
            else return       x.val;
        }
        return null;
    }

    public int size() {
        return size(root);
    }
    public int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public boolean contains(Key key) {
        return (get(key) != null);
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return 1 + rank(hi) - rank(lo);
        else              return rank(hi) - rank(lo);
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public int rank(Key key) {
        return rank(root, key);
    }
    public int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);

        if (cmp == 0)      return size(x.left);
        else if (cmp < 0)  return rank(x.left, key);
        else               return 1 + size(x.left) + rank(x.right, key);

    }

    // Ordered operations
    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }
    public Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }
    public Key max() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null)
            x = x.right;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);

        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else           return x;
    }
    private Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        inorder(q, root);
        return q;
    }
    private void inorder(Queue<Key> q, Node x) {
        if (x == null) return;
        inorder(q, x.left);
        q.enqueue(x.key);
        inorder(q, x.right);
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        keys(root, q, lo, hi);
        return q;
    }
    private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, q, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) q.enqueue(x.key);
        if (cmphi > 0) keys(x.right, q, lo, hi);
    }

    /***********************************************************************
     * deletion
     ***********************************************************************/

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("deleteMin() underflow!");
        root = deleteMin(root);
    }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    /* Hibbart deletion */
    public void delete(Key key) {
        delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    /***********************************************************************
     * check function
     ***********************************************************************/

    public boolean isBST() {
        return isBST(root, null, null);
    }
    public boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && min.compareTo(x.key) > 0) return false;
        if (max != null && max.compareTo(x.key) < 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    /***********************************************************************
     * unit test
     ***********************************************************************/

    public static void main(String[] args) {

        System.out.format("################################!\n");
        System.out.format("Test flight!\n");
        System.out.format("################################!\n");
        BST<String, String> st2 = new BST<String, String>();
        st2.put("09:00:00", "Chicago");
        st2.put("09:00:03", "Phoenix");
        st2.put("09:00:13", "Houston");
        st2.put("09:00:59", "Chicago");
        st2.put("09:01:10", "Houston");
        st2.put("09:03:13", "Chicago");
        st2.put("09:10:11", "Seattle");
        st2.put("09:10:25", "Seattle");
        st2.put("09:14:25", "Phoenix");
        st2.put("09:19:32", "Chicago");
        st2.put("09:19:46", "Chicago");
        st2.put("09:21:05", "Chicago");
        st2.put("09:22:43", "Seattle");
        st2.put("09:22:54", "Seattle");
        st2.put("09:25:52", "Chicago");
        st2.put("09:35:21", "Chicago");
        st2.put("09:36:14", "Seattle");
        st2.put("09:37:44", "Phoenix");

        for (String s : st2.keys()) {
            System.out.format("%s : %s\n", s, st2.get(s));
        }
        System.out.format("min: %s : %s\n", st2.min(), st2.get(st2.min()));
        System.out.format("max: %s : %s\n", st2.max(), st2.get(st2.max()));

        // for (String s : st2.keys("09:15:00", "09:25:00")) {
        //     System.out.format("%s : %s\n", s, st2.get(s));
        // }

        String key = "09:30:00";
        System.out.format("Celling of %s is %s : %s\n", key, st2.ceiling(key), st2.get(st2.ceiling(key)));

        key = "09:05:00";
        System.out.format("Floor of %s is %s : %s\n", key, st2.floor(key), st2.get(st2.floor(key)));

        System.out.format("The size of the symbol table is %d.\n", st2.size());
        key = "09:30:00";
        System.out.format("The rank of 09:30:00 is %d.\n", st2.rank(key));
        key = "09:00:00";
        System.out.format("The rank of 09:00:00 is %d.\n", st2.rank(key));
        // System.out.format("The 7th is %s : %s\n", st2.select(7), st2.get(st2.select(7)));

        System.out.format("################################!\n");
        System.out.format("deleteMin!\n");
        System.out.format("################################!\n");
        st2.deleteMin();
        for (String s : st2.keys()) {
            System.out.format("%s : %s\n", s, st2.get(s));
        }
        System.out.format("The size of the symbol table is %d.\n", st2.size());

        key = "09:22:54";
        System.out.format("################################!\n");
        System.out.format("delete key: %s!\n", key);
        System.out.format("################################!\n");
        st2.delete(key);
        for (String s : st2.keys()) {
            System.out.format("%s : %s\n", s, st2.get(s));
        }
        System.out.format("The size of the symbol table is %d.\n", st2.size());

        String lo = "09:15:00";
        String hi = "09:25:00";
        System.out.format("################################!\n");
        System.out.format("Test size(%s, %s), %d\n", lo, hi, st2.size(lo, hi));
        System.out.format("################################!\n");
        for (String s : st2.keys(lo, hi)) {
            System.out.format("%s : %s\n", s, st2.get(s));
        }
        System.out.format("The size of the symbol table is %d.\n", st2.size());

        lo = "09:14:25";
        hi = "09:25:52";
        System.out.format("################################!\n");
        System.out.format("Test size(%s, %s), %d\n", lo, hi, st2.size(lo, hi));
        System.out.format("################################!\n");
        for (String s : st2.keys(lo, hi)) {
            System.out.format("%s : %s\n", s, st2.get(s));
        }
        System.out.format("The size of the symbol table is %d.\n", st2.size());


    }

}
