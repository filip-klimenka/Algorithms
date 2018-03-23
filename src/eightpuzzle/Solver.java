// A* algorithm
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;

public class Solver {
    //SN(Board, moves, prev SN);
    private boolean solvable;
    private int totalMoves;
    private SearchNode last;
 
 
    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode prev;
        private int priority;
  
        public SearchNode(Board b, int m, SearchNode p){
            board = b;
            moves = m;
            prev  = p;
            priority = b.manhattan() + m;
        }
      
        // add compare to method
        public int compareTo(SearchNode other) {
            if (this.priority < other.priority) {return -1; }
            if (this.priority > other.priority) {return +1; }
            return 0;
        }
    }
 
    private void enqueueNodes(SearchNode node, MinPQ<SearchNode> mpq) {
        for (Board nb: node.board.neighbors()) {
            if ((node.prev == null) || (!nb.equals(node.prev.board))) {
                mpq.insert(new SearchNode(nb, node.moves + 1, node));
            }
        }
    }
 

    public Solver(Board initial) {
        // check
        if (initial == null) {
            throw new java.lang.IllegalArgumentException("argument is null") ;
        }
        
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();
        SearchNode FIRST = new SearchNode(initial, 0, null);
        SearchNode FIRSTTWIN = new SearchNode(initial.twin(), 0, null);
        pq.insert(FIRST);
        pqTwin.insert(FIRSTTWIN);

        while(true) {
            SearchNode SN = pq.delMin();
            SearchNode SNTWIN = pqTwin.delMin();
            
            // check if solution is obtained for the original search node
            if (SN.board.isGoal()) {
                last = SN;
                totalMoves = SN.moves;
                solvable = true;
                break;
            }
            // check if solution is obtained for the twin board
            if (SNTWIN.board.isGoal()) {
                totalMoves = -1;
                solvable = false;
                break;
            }
            
            enqueueNodes(SN, pq);
            enqueueNodes(SNTWIN, pqTwin);
        }   

  }
  
  public int moves() {
      return totalMoves;
  }
  
  public boolean isSolvable() {
      return solvable;
  }
  

  public Iterable<Board> solution() {
      Stack<Board> s = new Stack<Board>();
      if (!isSolvable()) {
          s = null;
      }
      else {
          SearchNode SN = last;
          s.push(SN.board);
          while(SN.prev != null) {
              s.push(SN.board);
              SN = SN.prev;
          }
      }
      return s;
  }

  public static void main(String[] args) {
      // create initial board from file
      In in = new In(args[0]);
      int n = in.readInt();
      int[][] blocks = new int[n][n];
      for (int i = 0; i < n; i++)
          for (int j = 0; j < n; j++)
              blocks[i][j] = in.readInt();
              Board initial = new Board(blocks);

      // solve the puzzle
      Solver solver = new Solver(initial);

      // print solution to standard output
      if (!solver.isSolvable())
          StdOut.println("No solution possible");
      else {
          StdOut.println("Minimum number of moves = " + solver.moves());
          for (Board board : solver.solution())
              StdOut.println(board);
          }
  }
  
}
