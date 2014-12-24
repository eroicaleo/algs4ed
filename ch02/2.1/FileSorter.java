import java.io.File;
public class FileSorter {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        File[] a = directory.listFiles();
        Sort.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i].getName());
    }
}
