public class BinaryHeapMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public BinaryHeapMaxPQ(int N) {
        pq = (Key[])new Comparable[N+1];
        N = 0; // Maybe redundant
    }

    private void swap(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    private boolean less(int i, int j) {
        return (pq[i].compareTo(pq[j]) < 0);
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k, k/2);
            k /= 2;
        }
    }
    public void insert(Key k) {
        if (N == pq.length) throw new IndexOutOfBoundsException("You insert too many keys!");
        pq[++N] = k;
        swim(N);
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j += 1;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
    public Key delMax() {
        if (N == 0) throw new IndexOutOfBoundsException("You cannot delete element in a pq without keys!");

        Key max = pq[1];
        swap(1, N--);
        sink(1);
        pq[N+1] = null;
        return max;
    }
    public static void main(String[] args) {
        BinaryHeapMaxPQ<Transaction> mpq = new BinaryHeapMaxPQ(20);
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction t = new Transaction(line);
            mpq.insert(t);
        }
        for (int i = 0; i < 5; i++) {
            mpq.delMax().print();
        }
    }
}
