public class Percolation {

    private int gridSize;
    private WeightedQuickUnionUF UF;
    private int[] siteStatus;
    private int[] botmStatus;
    private final int virtualTop;
    private final int virtualBot;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("grid size is non positive");

        gridSize = N;
        // including virtual top and bottom
        UF = new WeightedQuickUnionUF(gridSize*gridSize + 2);
        siteStatus = new int[gridSize*gridSize + 2];
        botmStatus = new int[gridSize*gridSize + 2];
        for (int i = 0; i < siteStatus.length; i++) {
            siteStatus[i] = 0;
            if (i > (gridSize-1)*gridSize) {
                botmStatus[i] = i;
            } else {
                botmStatus[i] = -1;
            }
        }
        virtualTop = 0;
        virtualBot = gridSize*gridSize+1;
        // virtual top and bottom are always open
        siteStatus[virtualTop] = 1;
        siteStatus[virtualBot] = 1;
    }
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        // 1. validate indices
        checkIndices(i, j);
        // 2. mark the site open
        siteStatus[xyTo1D(i, j)] = 1;
        // 3. Union
        // left
        if (j > 1 && isOpen(i, j-1))
            unionWrapper(xyTo1D(i, j), xyTo1D(i, j-1));
        // right
        if (j < gridSize && isOpen(i, j+1))
            unionWrapper(xyTo1D(i, j), xyTo1D(i, j+1));
        // up
        if (i == 1 || isOpen(i-1, j))
            unionWrapper(xyTo1D(i, j), xyTo1D(i-1, j));
        // down
        if (i < gridSize && isOpen(i+1, j))
            unionWrapper(xyTo1D(i, j), xyTo1D(i+1, j));

        // To prevent backwash
        int botmSite = botmStatus[UF.find(xyTo1D(i, j))];
        if (isFull(i, j) && isBottom(botmSite))
            UF.union(botmSite, virtualBot);

        return;
    }
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkIndices(i, j);
        return (siteStatus[xyTo1D(i, j)] == 1);
    }
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkIndices(i, j);
        return UF.connected(virtualTop, xyTo1D(i, j));
    }
    // does the system percolate?
    public boolean percolates() {
        return UF.connected(virtualTop, virtualBot);
    }
    private boolean isBottom(int p) {
        return p > (gridSize-1)*gridSize;
    }
    private void unionWrapper(int p, int q) {
        int rootp = UF.find(p);
        int rootq = UF.find(q);
        int botmp = botmStatus[rootp];
        int botmq = botmStatus[rootq];

        UF.union(p, q);
        rootp = UF.find(p);
        if (botmp > botmq) botmStatus[rootp] = botmp;
        else               botmStatus[rootp] = botmq;

        return;
    }
    private int xyTo1D(int i, int j) {
        // virtual top
        if (i == 0) return virtualTop;
        // virtual top
        if (i == gridSize+1) return virtualBot;
        // normal site
        return ((i-1)*gridSize + j);
    }
    private void checkIndices(int i, int j) {
        if (i <= 0 || i > gridSize)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > gridSize)
            throw new IndexOutOfBoundsException("col index j out of bounds");
        return;
    }
    // test client, optional
    public static void main(String[] args) {
        // Test run constructor, passed
        // Percolation p = new Percolation(-1);
        Percolation p = new Percolation(10);

        // Test checkIndices, passed
        // p.checkIndices(-1, 1);
        // p.checkIndices(1, 100);

        // Test open method
        p.open(1, 1);
        p.open(1, 2);
        if (p.UF.connected(p.xyTo1D(1, 1), p.xyTo1D(1, 2))) {
            StdOut.println("test passed!");
        }
        return;
    }
}
