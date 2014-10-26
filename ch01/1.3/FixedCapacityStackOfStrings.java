public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public void push(String item) {
        s[N++] = item;
    }
    public String pop() {
        String item = s[--N];
        s[N] = null;
        return item;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    private int size() {
        return N;
    }
    public static void main(String[] args) {
        StdOut.print("Hello FixedCapacityStackOfStrings!\n");
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(100);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop() + " ");
            else               stack.push(s);
        }
        StdOut.print("\n");
    }
}
