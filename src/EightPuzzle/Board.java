//import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;
//import java.util.Iterator;

public class Board {
    
    private int n;
    
    
    private final int[][] board;
    
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        n = blocks.length;
        board = deepCopy(blocks, n);
    }

    private int[][] deepCopy(int[][] y, int M) {
        int[][] out = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                out[i][j] = y[i][j];
            }
        }
        return out;
    }
    
    
    public int dimension() {
        return n;
    }
    
    // number of blocks out of place    
    public int hamming() {
        int hammingCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != i * n + j + 1) {
                    hammingCount++;
                }
            }
        }
        return --hammingCount;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattanCount = 0;
        int vDist;
        int hDist;
        int distance;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    vDist = java.lang.Math.abs(i - (board[i][j] - 1) / n);
                    hDist = java.lang.Math.abs(j - (board[i][j] - 1) % n);
                    distance = vDist + hDist;
                    manhattanCount = manhattanCount + distance;  
                }
            }
        }
        return manhattanCount;
    }
        
            
    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0;
    }
    
    private void swapBlocks(int[][] x, int i, int j, int p, int k) {
        int swap = x[i][j];
        x[i][j] = x[p][k];
        x[p][k] = swap;
    }
    
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int [][] boardCopy = deepCopy(board, n);
        if (boardCopy[0][0] * boardCopy[0][1] == 0) {
            // swap elements [1][0] and [1][1]
            swapBlocks(boardCopy, 1, 0, 1, 1);
        }
        else {
            // swap elements [0][0] and [0][1]
            swapBlocks(boardCopy, 0, 0, 0, 1);
        }       
        return new Board(boardCopy);
    }

    

    // does this board equal y?
    public boolean equals(Object y) {
        
        // same reference
        if (this == y) { return true; }
        
        if (y == null) {return false; }
        
        if (this.getClass() != y.getClass()) {return false; }
        
        Board that = (Board) y;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbours = new Stack<Board>();
        boolean blankFound = false;
        for (int i = 0; i < n && !blankFound; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    if (i > 0) pushBoardOntoStack(neighbours, i, j, i - 1, j);
                    if (j > 0) pushBoardOntoStack(neighbours, i, j, i, j - 1);
                    if (i < n - 1) pushBoardOntoStack(neighbours, i, j, i + 1, j);
                    if (j < n - 1) pushBoardOntoStack(neighbours, i, j, i, j + 1);
                    blankFound = true;
                    break;
                    
                }
            }
        }
        
        return neighbours;
    }
    
    private void pushBoardOntoStack(Stack<Board> s, int a, int b, int c, int d) {
        int[][] neighbourBoard  = deepCopy(board, n);
        swapBlocks(neighbourBoard, a, b, c, d);
        s.push(new Board(neighbourBoard));
    }
    // string representation of this board (in the output format specified below)
//    public String toString() {
//        String output = Integer.toString(dimension());
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (j == 0) {output = output + "\n"; } 
//                output = output + Integer.toString(board[i][j]);
//            }
//        }
//        return output;
//    }
    
    public String toString() {
        int N = n;
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    
    public static void main(String[] args){
        
        //System.out.println(7/3);
        //System.out.println(java.lang.Math.abs(-3));
        int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board a = new Board(blocks);
        StdOut.println(a.dimension());
        StdOut.println(a.hamming());
        StdOut.println(a.manhattan());
        StdOut.println("---A----");
        StdOut.println(a);
        Board b = a.twin();
        //StdOut.println(b);
        for (Board nb: a.neighbors()) {
            StdOut.println("-------");
            StdOut.println(nb);
        }
    }
}