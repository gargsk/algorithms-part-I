import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class Board {

    private int[][] tiles;

    private int blankPosition = -1;

    public Board(int[][] tiles) {
        if (tiles == null)
            throw new IllegalArgumentException("Board can't be initialized with null tiles");
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] == 0)
                    this.blankPosition = (i * this.tiles.length) + j;
            }
        }
        if (this.blankPosition == -1)
            throw new IllegalArgumentException("Board dont have empty tile");
    }

    // board dimension n
    public int dimension() {
        return this.tiles.length;
    }

    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                if (this.tiles[i][j] != 0 && this.tiles[i][j] != (j + (i * this.tiles.length) + 1))
                    hamming++;
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                if (this.tiles[i][j] != 0 && this.tiles[i][j] != (j + (i * this.tiles.length) + 1)) {
                    int valueAtij = this.tiles[i][j];
                    int iShouldBe = (valueAtij - 1) / this.tiles.length;
                    int jShouldBe = (valueAtij - 1) % this.tiles.length;
                    int horizontalMoveNeeded = Math.abs(i - iShouldBe);
                    int verticalMovesNeeded = Math.abs(j - jShouldBe);
                    manhattan = manhattan + horizontalMoveNeeded + verticalMovesNeeded;
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twinTiles = this.tiles;
        if (twinTiles[0][0] != 0 && twinTiles[0][1] != 0)
            return new Board(swap(0, 0, 0, 1));
        else
            return new Board(swap(1, 0, 1, 1));
    }

    private int[][] swap(int x1, int y1, int x2, int y2) {
        int[][] twinTiles = copyTiles();
        int temp = twinTiles[x1][y1];
        twinTiles[x1][y1] = twinTiles[x2][y2];
        twinTiles[x2][y2] = temp;
        return twinTiles;
    }

    private int[][] copyTiles() {
        int[][] twinTiles = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                twinTiles[i][j] = tiles[i][j];
            }
        }
        return twinTiles;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Board)) return false;
        Board board = (Board) that;
        if (blankPosition != board.blankPosition) return false;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbours = new Stack<>();

        int i = blankPosition / dimension();
        int j = blankPosition % dimension();

        if (i - 1 >= 0)
            neighbours.push(new Board(swap(i, j, i - 1, j)));
        if (i + 1 < dimension())
            neighbours.push(new Board(swap(i, j, i + 1, j)));
        if (j - 1 >= 0)
            neighbours.push(new Board(swap(i, j, i, j - 1)));
        if (j + 1 < dimension())
            neighbours.push(new Board(swap(i, j, i, j + 1)));

        return neighbours;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{8 ,1 ,3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(board.toString());
        System.out.println("Hamming " + board.hamming());
        System.out.println("Manhattan " + board.manhattan());
        System.out.println();
        for (Board board1 :
                board.neighbors()) {
            System.out.println(board1);
            System.out.println("Hamming " + board1.hamming());
            System.out.println("Manhattan " + board1.manhattan());
            System.out.println();
        }
    }
}
