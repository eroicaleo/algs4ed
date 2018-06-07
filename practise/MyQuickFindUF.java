import java.io.File;
import java.util.Scanner;

/**
 * Created by yg943079 on 6/1/18.
 */
public class MyQuickFindUF {
    private int[] id;
    private int count;

    public MyQuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        
        int pID = id[p];
        int qID = id[q];

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }

        count--;
    }
    
    public static void main(String[] args) {
        File file = new File("tinyUF.txt");
        try {
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            MyQuickFindUF uf = new MyQuickFindUF(n);
            while (scanner.hasNextInt()) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                    System.out.printf("%d %d", p, q);
                    System.out.printf("\n");
                }
            }
            System.out.printf("%d components", uf.count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
