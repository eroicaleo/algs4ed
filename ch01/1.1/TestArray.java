public class TestArray {
    public static void main(String[] args) {
        System.out.println("Hello java!");
        int[] a = new int[10];
        for (int i = 0; i < a.length; ++i) {
            a[i] = i;
        }
        printArray(a);
        int[] b = a;
        b[0] = 1024;
        printArray(a);
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
    }
}
