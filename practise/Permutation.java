import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // System.out.printf("There are %d integers\n", k);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            // StdOut.println(s);
            if (s.length() == 0) break;
            queue.enqueue(s);
        }
        // StdOut.println("real output");
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
