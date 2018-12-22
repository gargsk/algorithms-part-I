package tt;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {

    int dimension;

    int[][] blocks;

    public Board(int[][] blocks) {
        this.dimension = blocks.length;
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return this.dimension;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!(i == dimension - 1 && j == dimension - 1)) {
                    if (blocks[i][j] != (1 + (i * dimension) + j)) {
                        hamming++;
                    }
                }
            }
        }
        return hamming;
    }

    public int manhattan() {
        int manahttan = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int value = blocks[i][j];
                if (value != 0 && value != (1 + (i * dimension) + j)) {
                    int initialX = (value - 1) / dimension;
                    int initialY = value - 1 - initialX * dimension;
                    int distance = Math.abs(i - initialX)
                            + Math.abs(j - initialY);
                    manahttan += distance;
                }
            }
        }

        return manahttan;
    }

    public boolean isGoal() {
        return areEntriesEquals(this.blocks, this.getGoalBoard());
    }

    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        return null;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dimension);
        for (int i = 0; i < dimension; i++) {
            result = 31 * result + Arrays.hashCode(blocks[i]);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return dimension == board.dimension &&
                areEntriesEquals(blocks, board.blocks);
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = null;
        // first find the index of empty block or the block has value 0 in it
        int i, j = 0;
        boolean found = false;
        for (i=0;i < dimension; i++) {
            for (; j < dimension; j++) {
                if (this.blocks[i][j] == 0) ;
                found = true;
                break;
            }
            if (found)
                break;
        }

        // we have index of position contains null or 0
        // calculate neighbours with index manipulation and add them to list
        // top
        // bootom
        // left
        // right

        return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(this.blocks[i][j]);
                if (j < dimension - 1)
                    sb.append(" , ");
            }
        }
        return sb.toString();
    }


    private int[][] getGoalBoard() {
        int[][] goalBoard = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == dimension - 1 && j == dimension - 1) {
                    goalBoard[i][j] = 0;
                } else {
                    goalBoard[i][j] = 1 + (i * dimension) + j;
                }
            }
        }
        return goalBoard;
    }

    private boolean areEntriesEquals(int[][] blocks1, int[][] blocks2) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks1[i][j] != blocks2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}