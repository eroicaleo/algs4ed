public class Flip {
    public static Counter max(Counter x, Counter y) {
        if (x.tally() > y.tally())
            return x;
        else return y;
    }
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Counter head = new Counter("head");
        Counter tail = new Counter("tail");
        for (int i = 0; i < T; i++) {
            if (StdRandom.bernoulli(0.5))
                head.increment();
            else
                tail.increment();
        }
        StdOut.println(head);
        StdOut.println(tail);
        int d = head.tally() - tail.tally();
        StdOut.println("delta: "+Math.abs(d));
        StdOut.println(max(head, tail)+" wins");
    }
}
