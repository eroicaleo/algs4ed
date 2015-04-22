public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int N = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }
    public BinarySearchST(int N) {
        keys = (Key[]) new Comparable[N];
        vals = (Value[]) new Object[N];
    }

    public int rank(Key key) {
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)      hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else              return mid;
        }
        return lo;
    }

    // Basic operations
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return vals[i];
        return null;
    }
    private void resize(int capacity) {
        assert (capacity >= N);

        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];

        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }

        keys = tempk;
        vals = tempv;
    }
    public void put(Key key, Value val) {
        if (val == null) delete(key);

        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            vals[i] = val;
            return;
        }

        if (N == keys.length) resize(keys.length*2);

        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;

        N++;
    }
    public void delete(Key key) {
        if (isEmpty()) return;

        int i = rank(key);

        if (i == N || key.compareTo(keys[i]) >= 0) {
            return;
        }

        for (int j = i; j < N-1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;

        // Prevent loitering
        keys[N] = null;
        vals[N] = null;

        if (N > 0 && N == keys.length/4) resize(keys.length/2);
    }
    public boolean contains(Key key) {
        return (get(key) != null);
    }
    public boolean isEmpty() {
        return (size() == 0);
    }
    public int size() {
        return N;
    }
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    // Ordered operations
    public Key min() {
        if (isEmpty()) return null;
        return keys[0];
    }
    public Key max() {
        if (isEmpty()) return null;
        return keys[size()-1];
    }

    public Key select(int k) {
        if (k < N && k >= 0) return keys[k];
        else                 return null;
    }

    /* There is no need to check if isEmpty() because we check if (i < N) */
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i < N) return keys[i];
        return null;
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        if (lo == null && hi == null) return queue;
        if (lo == null) throw new NullPointerException("The lo is null in keys!");
        if (hi == null) throw new NullPointerException("The hi is null in keys!");
        if (hi.compareTo(lo) < 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi))
            queue.enqueue(hi);
        return queue;
    }

    public static void main(String[] args) {

        System.out.format("################################!\n");
        System.out.format("Test flight!\n");
        System.out.format("################################!\n");
        BinarySearchST<String, String> st2 = new BinarySearchST<String, String>();
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

        for (String s : st2.keys("09:15:00", "09:25:00")) {
            System.out.format("%s : %s\n", s, st2.get(s));
        }

        String key = "09:30:00";
        System.out.format("Celling of %s is %s : %s\n", key, st2.ceiling(key), st2.get(st2.ceiling(key)));

        key = "09:05:00";
        System.out.format("Floor of %s is %s : %s\n", key, st2.floor(key), st2.get(st2.floor(key)));
        System.out.format("The 7th is %s : %s\n", st2.select(7), st2.get(st2.select(7)));
    }

}
