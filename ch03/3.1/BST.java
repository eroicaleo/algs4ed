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
        Node x = root;
        while (x.left != null)
            x = x.left;
        return x.key;
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
    }


}
