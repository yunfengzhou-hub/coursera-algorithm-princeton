package percolation;

public class Percolation {
    private static Boolean[][] gridOpen;
    private static Boolean[][] gridFull;
    private static Boolean isPercolate;
    private static Integer numOpen,gridSize;
    private static Boolean needUpdate;
    GridRoot gridRoot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        gridOpen = new Boolean[n][n];
        gridFull = new Boolean[n][n];
        isPercolate = false;
        numOpen = 0;
        gridSize = n;
        gridRoot = new GridRoot(n);
        needUpdate = false;

        for(Integer i=0;i<n;i++){
            for(Integer j=0;i<n;i++){
                gridOpen[i][j] = false;
                gridFull[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(gridOpen[col-1][row-1]){
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
        for(Integer i=0;i<gridSize;i++){
            if(isOpen(i,gridSize-1)){
                gridIndex = gridRoot.getRoot(i,gridSize-1);
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
        for(Integer i=0;i<gridSize;i++){
            if(isFull(i,gridSize-1)){
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){

    }
}