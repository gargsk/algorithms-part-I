package coursera.edu.princeton.cs.algs.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int[][] grid;
    private int openSiteCount;
    private int topVirtualSiteIndex;
    private int bottomVirtualSiteIndex;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        this.grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        this.openSiteCount = 0;
        this.topVirtualSiteIndex = 0;
        this.bottomVirtualSiteIndex = (n * n) + 1;
    }

    public void open(int row, int col) {

        if (!isValidSite(row, col))
            throw new IllegalArgumentException();

        if (!isOpen(row, col)) {
            this.grid[row - 1][col - 1] = 1;
            openSiteCount++;
        }
        int currentSiteIndex = getSiteIndex(row, col);

        if (row == 1)
            this.weightedQuickUnionUF.union(currentSiteIndex, this.topVirtualSiteIndex);

        if (row == grid[0].length)
            this.weightedQuickUnionUF.union(currentSiteIndex, this.bottomVirtualSiteIndex);
        // union with adjacent open sites
        int adjSiteIndex;

        try {
            if (isOpen(row, col - 1)) { // left open
                adjSiteIndex = getSiteIndex(row, col - 1);
                this.weightedQuickUnionUF.union(adjSiteIndex, currentSiteIndex);
            }
        } catch (IllegalArgumentException iae) {
        }

        try {
            if (isOpen(row, col + 1)) { // right open
                adjSiteIndex = getSiteIndex(row, col + 1);
                this.weightedQuickUnionUF.union(adjSiteIndex, currentSiteIndex);
            }
        } catch (IllegalArgumentException iae) {
        }

        try {
            if (isOpen(row - 1, col)) { // upper open
                adjSiteIndex = getSiteIndex(row - 1, col);
                this.weightedQuickUnionUF.union(adjSiteIndex, currentSiteIndex);
            }
        } catch (IllegalArgumentException iae) {
        }

        try {
            if (isOpen(row + 1, col)) { // lower open
                adjSiteIndex = getSiteIndex(row + 1, col);
                this.weightedQuickUnionUF.union(adjSiteIndex, currentSiteIndex);
            }
        } catch (IllegalArgumentException iae) {
        }

    }

    public boolean isOpen(int row, int col) {
        if (!isValidSite(row, col))
            throw new IllegalArgumentException();
        return this.grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        if (!isValidSite(row, col))
            throw new IllegalArgumentException();
        int siteIndex = getSiteIndex(row, col);
        return this.weightedQuickUnionUF.connected(siteIndex, this.topVirtualSiteIndex);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return this.weightedQuickUnionUF.connected(this.bottomVirtualSiteIndex, this.topVirtualSiteIndex);
    }

    private boolean isValidSite(int row, int col) {
        if ((row > 0 & row <= grid[0].length) & (col > 0 & col <= grid[0].length))
            return true;
        else
            return false;
    }

    private Integer getSiteIndex(int row, int col) {
        return ((row - 1) * grid[0].length) + col;
    }

}
