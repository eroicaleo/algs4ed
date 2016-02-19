import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.In;

/**
 * Created by yangge on 2/19/2016.
 */
public class BSTQuiz {
    public static void main(String[] args) {
        In in = new In("BSTQuiz.txt");
        String[] q1 = in.readLine().split(" ");
        String[] q2 = in.readLine().split(" ");
        String[] q3 = in.readLine().split(" ");

        BST<Integer, String> bst1 = new BST<>();
        for (String s: q1) {
            bst1.put(Integer.parseInt(s), s);
        }
        Iterable<Integer> queue = bst1.levelOrder();
        for (Integer i : queue) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("#### Question 2 ####");
        BST<Integer, String> bst2 = new BST<>();
        for (String s: q2) {
            bst2.put(Integer.parseInt(s), s);
        }
        for (String s: q3) {
            Integer i = Integer.parseInt(s);
            bst2.delete(i);
        }
        queue = bst2.levelOrder();
        for (Integer i : queue) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
