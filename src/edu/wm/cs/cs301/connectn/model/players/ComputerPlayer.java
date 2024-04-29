/**
 * The computer player in a ConnectNGame. Implements a strategy for the player to follow when playing.
 */
package edu.wm.cs.cs301.connectn.model.players;

import edu.wm.cs.cs301.connectn.model.GameBoard;
import edu.wm.cs.cs301.connectn.model.Location;

import java.util.ArrayList;

public class ComputerPlayer implements Player {
    private char symbol;
    public ComputerPlayer(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public int takeTurn(GameBoard board) {
        // TODO implement
        return followStrategy(board);
    }

    private int followStrategy(GameBoard board) {
        int baseline = this.scoreBoard(board);
        int topScore = -1;
        int topMove = 0;
//		Iterate through columns
        for (int columnIndex = 0; columnIndex < board.getColumnCount(); columnIndex++) {
            if (!board.board[0][columnIndex].isEmpty()) {
                continue;
            }
            GameBoard boardCopy = new GameBoard(board);
            boardCopy.applyTurn(columnIndex, this.getSymbol());
            int candidateScore = this.scoreBoard(boardCopy) - baseline;
            if (candidateScore >= topScore) {
                topScore = candidateScore;
                topMove = columnIndex;
            }
        }

        return topMove;
    }

    private int scoreBoard(GameBoard board) {
        // Count longest consecutive chain of symbols
        Location[][] rows = board.board;
        Location[][] columns = board.getColumns();
        ArrayList<ArrayList<Location>> diagonals = board.getDiagonals();

        ArrayList<Location[]> spans = new ArrayList<Location[]>();

        for (Location[] row : rows) {
            spans.add(row);
        }

        for (Location[] column : columns) {
            spans.add(column);
        }

        for (ArrayList<Location> diagonal : diagonals) {
            Location[] diagonalArray = new Location[diagonal.size()];
            for (int i = 0; i < diagonal.size(); i++) {
                diagonalArray[i] = diagonal.get(i);
            }
            spans.add(diagonalArray);
        }

        int topInARow = 0;

        for (Location[] span : spans) {
            int count = 0;
            for (Location loc : span) {
                if (loc.getToken().equals(this.symbol)) {
                    count++;
                } else {
                    count = 0;
                }

                if (count > topInARow) {
                    topInARow = count;
                }
            }
        }

        return topInARow;
    }

        @Override
    public Character getSymbol() {
        return this.symbol;
    }
}
