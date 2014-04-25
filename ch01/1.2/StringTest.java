public class StringTest {
    public static boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1].compareTo(a[i]) > 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String s = "The decline and fall\tof\nRoman Empire";
        String[] words = s.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            StdOut.println(words[i]);
        }
        StdOut.println(isSorted(words));
    }
}
