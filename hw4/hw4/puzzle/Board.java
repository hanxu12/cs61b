package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] board;
    private int len;

    public Board(int[][] tiles) {
        if (tiles.length <= 0) {
            throw new IndexOutOfBoundsException("Out of bound");
        }
        len = tiles.length;
        board = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= len || j < 0 || j >= len) {
            throw new IndexOutOfBoundsException("Out of bound");
        }
        //0 represents BLANK square
        return board[i][j];
    }

    public int size() {
        return len;
    }

    private boolean checkBlank(int i, int j) {
        return tileAt(i, j) == 0;
    }

    /**
     * @Source http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int error = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!checkBlank(i, j) && (i * len + j + 1 != tileAt(i, j))) {
                    error += 1;
                }
            }
        }
        return error;
    }

    public int manhattan() {
        int error = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!checkBlank(i, j)) {
                    int temp = tileAt(i, j) - 1;
                    int expectRow = temp / len;
                    int expectCol = temp % len;
                    error = error + Math.abs(expectRow - i) + Math.abs(expectCol - j);
                }
            }
        }
        return error;
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board tempBoard = (Board) y;
        if (this.size() != tempBoard.size()) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (this.tileAt(i, j) != tempBoard.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    Purposeless code to fulfill style
     */
    @Override
    public int hashCode() {
        return 0;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
