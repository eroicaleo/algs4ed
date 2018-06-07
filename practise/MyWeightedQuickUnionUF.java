import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by yg943079 on 6/2/18.
 */
public class MyWeightedQuickUnionUF {
    private int[] parent;
    private int[] size;
    private int count;

    public MyWeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
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
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
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
