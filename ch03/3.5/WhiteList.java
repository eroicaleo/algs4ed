import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by yangge on 1/29/2016.
 */
public class WhiteList {
    public static void main(String[] args) {
        SET<String> set = new SET<>();

        System.out.println(System.getProperty("user.dir"));

        In in = new In("list.txt"); // http://algs4.cs.princeton.edu/35applications/list.txt
        while (!in.isEmpty()) {
            set.add(in.readString());
        }

        in = new In("tinyTale.txt"); // http://algs4.cs.princeton.edu/35applications/tinyTale.txt
        while (!in.isEmpty()) {
            String word = in.readString();
            if (set.contains(word))
                StdOut.println(word);
        }

    }
}
