import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by yg943079 on 6/1/18.
 */
public class MyQuickUnionUF {
    private int[] parent;
    private int count;

    public MyQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }

    public static void main(String[] args) {
        File file = new File("tinyUF.txt");
        try {
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            MyQuickUnionUF uf = new MyQuickUnionUF(n);
            while (scanner.hasNextInt()) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                    System.out.printf("%d %d", p, q);
                    System.out.printf("\n");
                }
            }
            System.out.printf("%d components.", uf.count());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
