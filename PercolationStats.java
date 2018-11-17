/* *****************************************************************************
 *  Name: LEE DONGHOON
 *  Date: 2018-11-17
 *  Description: Algorithm part1, Percolation assignment
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] x;

    private int n;
    private int trials;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.n = n;
        this.trials = trials;
        x = new double[this.trials];
        experiment(n, trials);
    }

    private void experiment(int n, int trials){

        for(int i = 0; i < trials; i++){
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if(!percolation.isOpen(row, col) && !percolation.isFull(row, col)){
                    percolation.open(row, col);
                }
            }
            int numOfOpens = percolation.numberOfOpenSites();
            x[i] = (double)numOfOpens / (n * n);
        }
    }


    // sample mean of percolation threshold
    public double mean(){

        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){

        return StdStats.stddev(x);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){

        return mean() - ((1.96 * stddev()) / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){

        return mean() + ((1.96 * stddev()) / Math.sqrt(this.trials));
    }

    public static void main(String[] args) {

    }
}
