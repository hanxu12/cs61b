package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    double[] statArr;
    int tempT;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Wrong input");
            //throw new RuntimeException("Ring Buffer Overflow");
        }
        Percolation a = pf.make(N);
        tempT = T;
        statArr = new double[T];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N * N; j++) {

                while (!a.percolates()) {
                int randRow = StdRandom.uniform(N);
                int randCol = StdRandom.uniform(N);
                while (!a.isOpen(randRow, randCol)){
                    randRow = StdRandom.uniform(N);
                    randCol = StdRandom.uniform(N);
                }
                a.open(StdRandom.uniform(N), StdRandom.uniform(N));
                }

                statArr[i] = a.numberOfOpenSites() / ((double) N * N);
            }
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
