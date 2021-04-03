package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IndexOutOfBoundsException;

public class Percolation {
    WeightedQuickUnionUF WQUF;
    boolean[][] openSites;
    int openSize;
    int N;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        WQUF = new WeightedQuickUnionUF(N * N + 2);
        openSites = new boolean[N][N];
        openSize = 0;
        this.N = N;
    }

    private void validate(int row, int col){
        if (row < 0 || col < 0){
            throw new IllegalArgumentException("Grid doesn't have negative values");
        } else if(row > N - 1 || col > N - 1){
            throw new IndexOutOfBoundsException("Index is outside the grid");
        }
    }

    private int oneDIndex(int row, int col){
        return row * N + col;
    }


    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        openSites[row][col] = true;
        openSize += 1;
        int mid = oneDIndex(row, col);

        if(row - 1 >= 0 && openSites[row - 1][col]){
            WQUF.union(mid, oneDIndex(row - 1, col));
        }
        if(col + 1 < N && openSites[row][col + 1]){
            WQUF.union(mid, oneDIndex(row, col + 1));
        }
        if(row + 1 < N && openSites[row + 1][col]){
            WQUF.union(mid, oneDIndex(row + 1, col));
        }
        if(col - 1 >= 0 && openSites[row][col - 1]){
            WQUF.union(mid, oneDIndex(row, col - 1));
        }

        if(row == 0){
            WQUF.union(oneDIndex(row, col), N * N);
        }
        if(row == N - 1){
            WQUF.union(oneDIndex(row, col), N * N + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row, col);
        return WQUF.connected(oneDIndex(row, col), N*N) && isOpen(row, col);
    }

    // number of open sites
    public int numberOfOpenSites(){
        return openSize;
    }

    // does the system percolate?
    public boolean percolates(){
        return WQUF.connected(N*N, N*N + 1);
    }

    public static void main(String[] args){

    }   // use for unit testing (not required, but keep this here for the autograder)
}
