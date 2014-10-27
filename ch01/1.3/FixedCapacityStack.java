public class FixedCapacityStack<Item> {
    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity) {
        s = (Item [])new Object[capacity];
    }

    public void push(Item item) {
        s[N++] = item;
    }
    public Item pop() {
        Item item = s[--N];
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
        StdOut.print("Hello FixedCapacityStack!\n");
        FixedCapacityStack<String> stack = new FixedCapacityStack(100);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop() + " ");
            else               stack.push(s);
        }
        StdOut.print("\n");
    }
}
