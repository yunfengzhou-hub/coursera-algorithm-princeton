package percolation;

public class GridRoot{
    private static Integer[][][] gridRoot;
    public GridRoot(int n){
        gridRoot = new Integer[n][n][2];
        for(Integer i=0;i<n;i++){
            for(Integer j=0;j<n;j++){
                gridRoot[i][j][0] = i;
                gridRoot[i][j][1] = j;
            }
        }
    }
    public Integer[] getRoot(int row, int col){
        Integer[] rootIndex = new Integer[2];
        rootIndex[0] = col - 1;
        rootIndex[1] = row - 1;
        while(gridRoot[rootIndex[0]][rootIndex[1]][0] != rootIndex[0] || 
        gridRoot[rootIndex[0]][rootIndex[1]][1] != rootIndex[1]){
            rootIndex[0] = gridRoot[rootIndex[0]][rootIndex[1]][0];
            rootIndex[1] = gridRoot[rootIndex[0]][rootIndex[1]][1];
        }
        return rootIndex;
    }
    public void setRoot(int row, int col, Integer[] newIndex){
        Integer[] oldIndex = getRoot(row,col);
        gridRoot[oldIndex[0]][oldIndex[1]][0] = newIndex[0];
        gridRoot[oldIndex[0]][oldIndex[1]][1] = newIndex[1];
    }
    public boolean equalRoot(int row1, int col1, int row2, int col2){
        Integer[] root1 = getRoot(row1,col1);
        Integer[] root2 = getRoot(row2,col2);
        return root1[0]==root2[0] && root1[1]==root2[1];
    }
    // following function could be changed according to different
    // union-find strategy.
    public void mergeRoot(int row1, int col1, int row2, int col2){
        if(equalRoot(row1, col1, row2, col2)){
            return;
        }
        setRoot(row2, col2, getRoot(row1,col1));
    }
}