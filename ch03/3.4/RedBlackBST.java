import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean BLACK = false;
    private static final boolean RED   = true;

    private class Node {
        private Key     key;
        private Value   val;
        private Node    left;
        private Node    right;
        private boolean color;
        private int     N;

        public Node(Key key, Value val, boolean color, int N) {
            this.key   = key;
            this.val   = val;
            this.color = color;
            this.N     = N;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED, 1);
        
        int cmp = key.compareTo(h.key);
        if      (cmp == 0) h.val   = val;
        else if (cmp < 0)  h.left  = put(h.left, key, val);
        else               h.right = put(h.right, key, val);

        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColor(h); 

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    public void delete(Key key) {
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;

        /* I am a 3-node or 4-node */
        if (!isRed(h.left) && !isRed(h.left.left))
            /* way down: make sure */
            moveLeft(h);

        h.left = deleteMin(h.left);

        /* way up: restore the red-black tree invariant */
        balance(h);
        return balance(h);

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) h = flipColor(h);
    }

    private Node moveRedLeft(Node h) {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /*************************************************************************
     *  red-black tree helper functions
     *************************************************************************/
    private Node rotateRight(Node h) {
        assert (h != null) && (isRed(h.left));

        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }
    private Node rotateLeft(Node h) {
        assert (h != null) && (isRed(h.right));

        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }
    private void flipColor(Node h) {
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) && isRed(h.left) && isRed(h.right)) || (isRed(h) && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /*************************************************************************
     *  Node helper methods
     *************************************************************************/
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public int size() {
        return size(root);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        return queue;
    }

    /*************************************************************************
     *  Ordered symbol table methods.
     *************************************************************************/
    public Key min() {
        if (isEmpty()) return null;
        else           return min(root).key;
    }
    private Node min(Node x) {
        if (x.left != null) return min(x.left);
        else                return x;
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
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else           return x.key;
    }
}
