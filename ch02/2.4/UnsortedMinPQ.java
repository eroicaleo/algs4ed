public class UnsortedMinPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;

    public UnsortedMinPQ(int N) {
        pq = (Key[])new Comparable[N];
        N = 0;
    }

    public void insert(Key key) {
        if (N == pq.length) throw new IndexOutOfBoundsException("You insert too many keys!");
        pq[N++] = key;
    }

    public boolean isEmpty() {
        return (N == 0);
    }

    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }
    private static void swap(Comparable[] a, int i, int j) {
        Comparable swp = a[i];
        a[i] = a[j];
        a[j] = swp;
    }
    public Key delMin() {
        if (N == 0) throw new IndexOutOfBoundsException("You cannot delete element in a pq without keys!");

        int minIndex = 0;
        for (int i = 0; i < N; i++) {
            if (less(pq[i], pq[minIndex]))
                minIndex = i;
        }
        Key key = pq[minIndex];
        swap(pq, minIndex, N-1);
        pq[N--] = null;
        return key;
    }
    public static void main(String[] args) {
        UnsortedMinPQ<Transaction> mpq = new UnsortedMinPQ(20);
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction t = new Transaction(line);
            mpq.insert(t);
        }
        for (int i = 0; i < 5; i++) {
            mpq.delMin().print();
        }
    }
}
