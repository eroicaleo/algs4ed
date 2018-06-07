import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by yg943079 on 6/3/18.
 */
public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf1;
    private int n;
    private int numberOfOpenSites;
    private boolean[] open;
    private final int virtualTop;
    private final int virtualBot;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("int n cannot be less than 1");
        this.n = n;
        virtualTop = 0;
        virtualBot = n * n + 1;
        uf = new WeightedQuickUnionUF(virtualBot+1);
        uf1 = new WeightedQuickUnionUF(virtualBot+1);
        open = new boolean[virtualBot+1];
        for (int i = 0; i < open.length; i++) {
            open[i] = false;
        }
        open[virtualTop] = true;
        open[virtualBot] = true;
        numberOfOpenSites = 0;
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("row or col is out of range");
    }

    private int rcToIndex(int row, int col) {
        if (row < 1)      return virtualTop;
        else if (row > n) return virtualBot;

        if (col < 1) col = 1;
        else if (col > n) col = n;
        return  (row-1)*n + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;

        int index  = rcToIndex(row, col);
        int up     = rcToIndex(row-1, col);
        int bottom = rcToIndex(row+1, col);
        int left   = rcToIndex(row, col-1);
        int right  = rcToIndex(row, col+1);

        open[index] = true;
        numberOfOpenSites++;

        if (open[up])     uf.union(index, up);
        if (open[bottom]) uf.union(index, bottom);
        if (open[left])   uf.union(index, left);
        if (open[right])  uf.union(index, right);

        if (open[up])                            uf1.union(index, up);
        if (bottom < virtualBot && open[bottom]) uf1.union(index, bottom);
        if (open[left])                          uf1.union(index, left);
        if (open[right])                         uf1.union(index, right);

    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return open[rcToIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf1.connected(virtualTop, rcToIndex(row, col));
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return uf.connected(virtualBot, virtualTop);
    }

    public static void main(String[] args) {
    }
}
