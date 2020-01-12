package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int openSitesCnt;
    private WeightedQuickUnionUF checker1;
    private WeightedQuickUnionUF checker2;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Wrong input");
        }
        grid = new boolean[N][N];
        //initialize the grid
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        openSitesCnt = 0;
        //checker1 checks isFull, checker2 checks whether percolates
        checker1 = new WeightedQuickUnionUF(N * N + 1);
        checker2 = new WeightedQuickUnionUF(N * N + 2);
        //virtual top site for both checkers
        for (int i = 0; i < N; i++) {
            checker1.union(N * N, i);
        }
        for (int i = 0; i < N; i++) {
            checker2.union(N * N, i);
        }
        //virtual btm site
        for (int i = 1; i <= N; i++) {
            checker2.union(N * N + 1, N * N - i);
        }
    }

    public void open(int row, int col) {
        if (!checkValid(row, col)) {
            throw new IndexOutOfBoundsException("Out of bound");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        openSitesCnt += 1;
        //calculate row
        int currIndex = getIndex(row, col);

        //connect neighbour open sites
        if (checkValid(row - 1, col) && isOpen(row - 1, col)) {
            checker1.union(currIndex, getIndex(row - 1, col));
            checker2.union(currIndex, getIndex(row - 1, col));
        }
        if (checkValid(row + 1, col) && isOpen(row + 1, col)) {
            checker1.union(currIndex, getIndex(row + 1, col));
            checker2.union(currIndex, getIndex(row + 1, col));
        }
        if (checkValid(row, col - 1) && isOpen(row, col - 1)) {
            checker1.union(currIndex, getIndex(row, col - 1));
            checker2.union(currIndex, getIndex(row, col - 1));
        }
        if (checkValid(row, col + 1) && isOpen(row, col + 1)) {
            checker1.union(currIndex, getIndex(row, col + 1));
            checker2.union(currIndex, getIndex(row, col + 1));
        }
    }

    private int getIndex(int row, int col) {
        return (row * grid.length + col);
    }

    private boolean checkValid(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            return false;
        }
        return true;
    }

    public boolean isOpen(int row, int col) {
        if (!checkValid(row, col)) {
            throw new IndexOutOfBoundsException("Out of bound");
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (!checkValid(row, col)) {
            throw new IndexOutOfBoundsException("Out of bound");
        }
        int n = grid.length;
        return (isOpen(row, col) && checker1.connected(n * n, getIndex(row, col)));
//        for (int i = 0; i < grid.length; i++){
//            if (checker1.connected(i, getIndex(row, col)) == true){
//                return true;
//            }
//
//        }
//        return false;
    }

    public int numberOfOpenSites() {
        return openSitesCnt;
    }

    public boolean percolates() {
        if (grid.length == 1) {
            return isFull(0, 0);
        }
        return checker2.connected(grid.length * grid.length + 1, grid.length * grid.length);
//        for (int i = 0; i < grid.length; i++){
//            if (isFull(grid.length-1, i)){
//                return true;
//            }
//        }
//        return false;
    }

    public static void main(String[] args) {

    }


}
