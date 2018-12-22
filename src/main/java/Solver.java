import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private final Stack<Board> solutionBoards;

    private boolean isSolvable;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode previousSearchNode;
        private int moves;
        private int manhattan;

        public SearchNode(Board board, SearchNode previousSearchNode) {
            this.board = board;
            this.previousSearchNode = previousSearchNode;
            if (previousSearchNode == null)
                this.moves = 0;
            else
                this.moves = previousSearchNode.moves + 1;
            this.manhattan = board.manhattan();
        }


        @Override
        public int compareTo(SearchNode that) {
            if (that == null)
                return 0;
            else {
                int priorityDiff = (this.manhattan + this.moves) - (that.manhattan + that.moves);
                return priorityDiff == 0 ? this.manhattan - that.manhattan : priorityDiff;
            }
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException("solver called with null initial board");
        this.solutionBoards = new Stack<>();
        this.isSolvable = false;
        MinPQ<SearchNode> searchNodes = new MinPQ<>();
        // insert initial search node to q
        searchNodes.insert(new SearchNode(initial, null));
        searchNodes.insert(new SearchNode(initial.twin(), null));

        while (!searchNodes.min().board.isGoal()) {
            SearchNode searchNode = searchNodes.delMin();
            for (Board board : searchNode.board.neighbors()) {
                if (searchNode.previousSearchNode == null|| searchNode.previousSearchNode != null && !searchNode.previousSearchNode.board.equals(board))
                    searchNodes.insert(new SearchNode(board, searchNode));
            }
        }

        SearchNode current = searchNodes.min();
        while (current.previousSearchNode != null) {
            solutionBoards.push(current.board);
            current = current.previousSearchNode;
        }
        solutionBoards.push(current.board);
        if (current.board.equals(initial))
            isSolvable = true;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable())
            return this.solutionBoards.size()-1;
        else
            return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable())
            return this.solutionBoards;
        else
            return null;
    }

    // solve a slider puzzle (given below)

    public static void main(String[] args) {
        Board initial = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        //Board initial = new Board(new int[][]{{1,2,3}, {4,5,6}, {8,7,0}});
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
