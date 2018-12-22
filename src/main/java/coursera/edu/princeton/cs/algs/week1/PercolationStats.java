package coursera.edu.princeton.cs.algs.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int trials;
    private int gridSize;
    private double[] openSitesfractionEach;
    private double mean = -1;
    private double stddev = -1;
    private double confidenceLo;
    private double confidenceHi;

    private boolean isSimRan = false;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || n > Integer.MAX_VALUE)
            throw new IllegalArgumentException();
        if (trials <= 0 || trials > Integer.MAX_VALUE)
            throw new IllegalArgumentException();
        this.gridSize = n;
        this.trials = trials;
        this.openSitesfractionEach = new double[trials];
    }

    public static void main(String[] args) {
        int gridSize = 0;
        int trials = 0;
        try {
            gridSize = Integer.valueOf(args[0]).intValue();
            trials = Integer.valueOf(args[1]).intValue();
        } catch (NumberFormatException e) {
        }
        if (gridSize <= 0)
            throw new IllegalArgumentException();
        if (trials <= 0)
            throw new IllegalArgumentException();

        PercolationStats ps = new PercolationStats(gridSize, trials);

        ps.mean = ps.mean();
        ps.stddev = ps.stddev();
        ps.confidenceLo = ps.confidenceLo();
        ps.confidenceHi = ps.confidenceHi();
        System.out.println("mean                    = " + ps.mean);
        System.out.println("stddev                  = " + ps.stddev);
        System.out.println("95% confidence interval = " + ps.confidenceLo + " , " + ps.confidenceHi);

    }

    public double mean() {
        if (!isSimRan)
            runSimulation();
        mean = StdStats.mean(openSitesfractionEach);
        return mean;
    }

    public double stddev() {
        if (!isSimRan)
            runSimulation();
        stddev = StdStats.stddev(openSitesfractionEach);
        return stddev;
    }

    public double confidenceLo() {
        if (!isSimRan)
            runSimulation();
        if (mean == -1)
            mean();
        if (stddev == -1)
            stddev();
        confidenceLo = (this.mean - ((1.96 * this.stddev) / Math.sqrt(this.trials)));
        return confidenceLo;
    }

    public double confidenceHi() {
        if (!isSimRan)
            runSimulation();
        if (mean == -1)
            mean();
        if (stddev == -1)
            stddev();
        confidenceHi = (this.mean + ((1.96 * this.stddev) / Math.sqrt(this.trials)));
        return confidenceHi;
    }

    private void runSimulation() {
        isSimRan = true;
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(this.gridSize);
            while (!percolation.percolates()) {
                int j = StdRandom.uniform(1, this.gridSize + 1);
                int k = StdRandom.uniform(1, this.gridSize + 1);
                percolation.open(j, k);
            }
            double openSites = percolation.numberOfOpenSites();
            double totalSites = this.gridSize * this.gridSize;
            openSitesfractionEach[i] = (openSites / totalSites);
        }
    }
}
