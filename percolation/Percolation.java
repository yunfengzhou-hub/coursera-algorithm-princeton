package percolation;

import java.util.*;

public class Percolation {
    private static Boolean[][] gridOpen;
    private static Boolean[][] gridFull;
    private static Integer numOpen,gridSize;
    private static Boolean needUpdate;
    GridRoot gridRoot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        gridOpen = new Boolean[n][n];
        gridFull = new Boolean[n][n];
        numOpen = 0;
        gridSize = n;
        gridRoot = new GridRoot(n);
        needUpdate = false;

        for(Integer i=0;i<n;i++){
            for(Integer j=0;j<n;j++){
                gridOpen[i][j] = false;
                gridFull[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(isOpen(row,col)){
            return;
        }

        numOpen++;
        gridOpen[col-1][row-1] = true;
        needUpdate = true;

        if(row>1 && isOpen(row-1,col)){
            gridRoot.mergeRoot(row, col, row-1, col);
        }
        if(row<gridSize && isOpen(row+1,col)){
            gridRoot.mergeRoot(row, col, row+1, col);
        }
        if(col>1 && isOpen(row,col-1)){
            gridRoot.mergeRoot(row, col, row, col-1);
        }
        if(col<gridSize && isOpen(row,col+1)){
            gridRoot.mergeRoot(row, col, row, col+1);
        }
        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return gridOpen[col-1][row-1];
    }

    private void updateGrid(){
        if(!needUpdate){
            return;
        }
        Integer[] gridIndex;
        for(Integer i=1;i<=gridSize;i++){
            if(isOpen(i,1)){
                gridIndex = gridRoot.getRoot(i,1);
                gridFull[gridIndex[0]][gridIndex[1]] = true;
            }
        }
        needUpdate = false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        updateGrid();
        Integer[] gridIndex = gridRoot.getRoot(row,col);
        return gridFull[gridIndex[0]][gridIndex[1]];
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numOpen.intValue();
    }

    // does the system percolate?
    public boolean percolates(){
        updateGrid();
        for(Integer i=1;i<=gridSize;i++){
            if(isFull(i,gridSize)){
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) throws Exception{
        Percolation p = new Percolation(5);
        p.open(1, 1);
        if(p.numberOfOpenSites()!=1){
            System.exit(1);
        }
        p.open(1,2);
        p.open(2,2);
        p.open(3,2);
        p.open(3,1);
        p.open(4,1);
        p.open(5,1);
        p.open(5,2);
        p.open(5,3);
        p.open(5,4);
        if(p.numberOfOpenSites()!=10){
            // System.out.println("test failed");
            System.exit(1);
        }
        if(!p.isFull(5, 4)){
            System.out.println(p.isFull(4, 5));
            // System.out.println(Arrays.deeptoString(gridFull));
            // System.out.println("test failed");
            System.exit(1);
        }
        if(p.percolates()){
            System.out.println("test failed");
            System.exit(1);
        }
        p.open(5,5);
        if(!p.percolates()){
            System.out.println("test failed");
            System.exit(1);
        }
        System.out.println("test succeed");
    }
}