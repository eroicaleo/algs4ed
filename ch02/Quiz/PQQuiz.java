import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;

/**
 * Created by yangge on 2/19/2016.
 */
public class PQQuiz {
    public static void main(String[] args) {
        In in = new In("PQQuiz.txt");
        String[] q1 = in.readLine().split(" ");
        String[] q2 = in.readLine().split(" ");
        String[] q3 = in.readLine().split(" ");

        MyMaxPQ<Integer> pq1  = new MyMaxPQ<>();
        for (String s: q1) {
            pq1.insert(Integer.parseInt(s));
        }
        for (String s: q2) {
            pq1.insert(Integer.parseInt(s));
        }
        for (int k = 1; k <= pq1.size(); k++)
            System.out.print(pq1.get(k) + " ");
        System.out.println();

        System.out.println("#### Question 2 ####");
        MyMaxPQ<Integer> pq2 = new MyMaxPQ<>();
        for (String s: q3) {
            pq2.insert(Integer.parseInt(s));
        }
        for (int i = 0; i < 3; i++)
            pq2.delMax();
        for (int k = 1; k <= pq2.size(); k++)
            System.out.print(pq2.get(k) + " ");
        System.out.println();

    }
}
