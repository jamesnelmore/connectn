/**
 * Stores and controls the state of a single game. Tracks the board and win conditions.
 * For flow-of-play controls see ConnectNGame class.
 */
package edu.wm.cs.cs301.connectn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GameBoard {
    public Location[][] board;			//do not change!
    private int moves; // number of pieces on the board.
    private final int n; // number of pieces in a row/column needed to win. The n in Connect-N
    private char lastPlayedSymbol;
    public enum MoveResult {
        MOVEAPPLIED,
        GAMEWON,
        INVALIDMOVE,
        TIE;
    }

    public GameBoard(int rows, int columns, int n) {
        // loop through board and create an empty location in each spot
        board = new Location[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                this.board[row][column] = new Location();
            }
        }
        this.moves = 0;
        this.n = n;
    }

    public GameBoard(GameBoard board) {
        this.moves = board.moves;
        this.n = board.n;
        this.lastPlayedSymbol = board.lastPlayedSymbol;

        int rows = board.board.length;
        int columns = board.board[0].length;


        this.board = new Location[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                this.board[row][column] = new Location();
                this.board[row][column].placeToken(board.board[row][column].getToken());
            }
        }
    }

    // Getters
    public Location[][] getColumns() {
        /**
         * @return transposed game board
         */
        Location[][] transposedBoard = new Location[this.getColumnCount()][this.getRowCount()];
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                transposedBoard[j][i] = this.board[i][j];
            }
        }
        return transposedBoard;
    }


    public ArrayList<ArrayList<Location>> getDiagonals() {
//		start at top left corner and add each diagonal to a list
//		Do the same starting at the top right

        ArrayList<ArrayList<Location>> diagonals = new ArrayList<ArrayList<Location>>();

        int maxIndexSum = this.getRowCount() + this.getColumnCount() - 2;

        for (int sum=0; sum <= maxIndexSum; sum++) {
            ArrayList<Location> diagonal = new ArrayList<Location>();
            for (int j=0; j <= sum; j++) {
                if (j < this.getRowCount() && (sum - j) < this.getColumnCount()) {
                    diagonal.add(this.board[j][sum-j]);
                }
            }
            diagonals.add(diagonal);
        }
        return diagonals;
    }

    public int getRowCount() {
        return board.length;
    }

    public int getColumnCount() {
        return board[0].length;
    }

    public int getTurn() {
        return (this.moves / 2) + 1;
    }

    public int getN() {
        return n;
    }

    private String getDivider(char divider) {
        return " " + Character.toString(divider).repeat((this.getColumnCount() * 4) + 1) + " ";
    }

    public void displayBoard() {
        List<String> lines = new ArrayList<String>(); // lines that will be printed

        lines.add("Turn " + this.getTurn());

        // Generate Column numbers
        int[] columnNumbers = (int[]) IntStream.range(1, this.getColumnCount() + 1).toArray();

        List<String> columnLabels = new ArrayList<String>();

        for (int i : columnNumbers) {
            columnLabels.add("   " + i);
        }

        lines.add(String.join("", columnLabels));

        // Major separator
        lines.add(this.getDivider('='));

//		String rowLine = "|" + "|   ".repeat(this.getColumns()) + "||";
        for (Location[] row : this.board) {
            String rowline = "|";
            for (Location location : row) {
                rowline += "| " + location.toString() + " ";
            }
            lines.add(rowline + "||");

            lines.add(this.getDivider('-'));
        }
        lines.remove(lines.size() - 1);
        lines.add(this.getDivider('='));


        // Print Lines
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public String toString() {
        return java.util.Arrays.deepToString(this.board);
    }

    public Boolean checkForWinner() {
        // iterate through rows and count
        // If count

        //Through rows
        for (Location[] row : this.board) {
            // build row array and send to isWinningOrder
            if (this.isWinningOrder(row)) {
                return true;
            }
        }

        // Through columns
        for (Location[] column : this.getColumns()) {
            if (this.isWinningOrder(column)) {
                return true;
            }
        }

        // Through diagonals
        for (ArrayList<Location> diagonal : this.getDiagonals()) {
            if (this.isWinningOrder(diagonal)) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isNingOrder(ArrayList<Location> row, int N, char symbol) {
        if (row.size() < N) { return false; }

        int count = 0;
        for (Location loc : row) {
            if (loc.getToken().equals(symbol)) {
                count++;
            } else {
                count = 0;
            }

            if (count >= N) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isNingOrder(Location[] row, int N, char symbol) {
        /*
         * Returns true if row has at least N symbols in next to each other, else false.
         */
        ArrayList<Location> rowList = new ArrayList<Location>();
        for (Location i : row) {
            rowList.add(i);
        }
        return GameBoard.isNingOrder(rowList, N, symbol);
    }

    private Boolean isWinningOrder(ArrayList<Location> row) {
        return isNingOrder(row, this.n, this.lastPlayedSymbol);
    }

    private Boolean isWinningOrder(Location[] row) {
        ArrayList<Location> rowList = new ArrayList<Location>();
        for (Location i : row) {
            rowList.add(i);
        }
        return this.isWinningOrder(rowList);
    }

    public MoveResult applyTurn(int move, Character symbol) {
        /**
         * "Drops" given symbol down column move
         * @param  move   the 0-indexed column the player wants to make a move in
         * @param  symbol a character represented the player's symbol
         * @return true if a valid move was passed, otherwise false
         */

        if (move >= this.getColumnCount()) {
            return MoveResult.INVALIDMOVE;
        }
        // find lowest empty spot in column (move)
        // place token in spot if not full
        this.lastPlayedSymbol = symbol;

        for (int i = this.getRowCount() - 1; i >= 0; i--) {
            Location loc = this.board[i][move];
            if (loc.isEmpty()) { // Move is valid; will be placed
                loc.placeToken(symbol);

                if (this.checkForWinner()) {
                    return MoveResult.GAMEWON;
                } else {
                    this.moves++;
                    return (this.isFull()) ? MoveResult.TIE : MoveResult.MOVEAPPLIED;
                }
            }
        }
        return MoveResult.INVALIDMOVE; // Selected column was full so move is invalid.
    }

    private boolean isFull() {
        for (Location topLoc : this.board[0]) {
            if (topLoc.isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
