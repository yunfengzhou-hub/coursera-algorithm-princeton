package percolation;

import java.util.Random;

public class PercolationStats {
    private static Double[] thresh;
    private static Double pMean,pStddev,pLo,pHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        thresh = new Double[trials];
        Random r = new Random();
        for(Integer i=0;i<trials;i++){
            Percolation p = new Percolation(n);
            while(!p.percolates()){
                // System.out.println("to open");
                p.open(r.nextInt(n)+1, r.nextInt(n)+1);
                // p.show();
            }
            // System.out.println("system percolated.");
            thresh[i] = Double.valueOf(p.numberOfOpenSites())/Math.pow(n,2);
        }

        pMean=0.0;
        for(Integer i=0;i<trials;i++){
            pMean += thresh[i];
        }
        pMean = pMean/Double.valueOf(trials);

        pStddev = 0.0;
        for(Integer i=0;i<trials;i++){
            pStddev += Math.pow(thresh[i] - pMean, 2);
        }
        pStddev /= Double.valueOf(trials-1);
        pStddev = Math.pow(pStddev,0.5);

        pLo = pMean - 2.0 * pStddev;
        pHi = pMean + 2.0 * pStddev;
    }

    // sample mean of percolation threshold
    public double mean(){
        return pMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return pStddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return pLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return pHi;
    }

   // test client (see below)
   public static void main(String[] args){
       PercolationStats p = new PercolationStats(100, 1000);
    //    for(int i=0;i<10;i++){
    //        System.out.println(thresh[i]);
    //    }
       System.out.println(p.mean()+" "+p.stddev()+" "+p.confidenceLo()+" "+p.confidenceHi());
       return;
   }

}