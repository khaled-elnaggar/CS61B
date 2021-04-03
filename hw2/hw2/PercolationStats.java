package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    double[] xT;
    Percolation p;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        xT = new double[T];

        for(int i = 0; i < T; i++){
            p = pf.make(N);
            while(true){
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
                if(p.percolates()){
                    xT[i] = p.numberOfOpenSites() * 1.0 / (N * N);
                    break;
                }
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xT);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xT);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(xT.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(xT.length);
    }
}

