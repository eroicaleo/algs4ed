public class LinkedStackOfStrings {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }
    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }
    public boolean isEmpty() {
        return first == null;
    }
    private int size() {
        return 0;
    }
    public static void main(String[] args) {
        StdOut.print("Hello LinkedStackOfStrings!\n");
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop() + " ");
            else               stack.push(s);
        }
        StdOut.print("\n");
    }
}
