public class StackOfStrings {
    public StackOfStrings() {
    }
    public void push(String item) {
        return;
    }
    public String pop() {
        return "";
    }
    public boolean isEmpty() {
        return true;
    }
    private int size() {
        return 0;
    }
    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("_")) StdOut.print(stack.pop());
            else               stack.push(s);
        }
    }
}
