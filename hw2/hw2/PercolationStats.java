package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] statArr;
    private int tempT;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Wrong input");
            //throw new RuntimeException("Ring Buffer Overflow");
        }
        tempT = T;
        statArr = new double[T];
        for (int i = 0; i < statArr.length; i++) {
            Percolation percolationSample = pf.make(N);
            while (!percolationSample.percolates()) {
//                int indexOpen = StdRandom.uniform(N * N);
//                int randRow = indexOpen / N;
//                int randCol = indexOpen % N;
                int randRow = StdRandom.uniform(N);
                int randCol = StdRandom.uniform(N);
                percolationSample.open(randRow, randCol);
            }
            statArr[i] = percolationSample.numberOfOpenSites() / ((double) N * N);
        }
    }

    public double mean() {
        return StdStats.mean(statArr);
    }

    public double stddev() {
        return StdStats.stddev(statArr);
    }

    public double confidenceLow() {
        return  (mean() - 1.96 * stddev() / Math.sqrt(tempT));
    }

    public double confidenceHigh() {
        return  (mean() + 1.96 * stddev() / Math.sqrt(tempT));
    }
}
