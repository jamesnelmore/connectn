/**
 * The computer player in a ConnectNGame. Implements a strategy for the player to follow when playing.
 */
package edu.wm.cs.cs301.connectn.model.players;

import edu.wm.cs.cs301.connectn.model.GameBoard;

import java.util.ArrayList;

public class ComputerPlayer implements Player {
    // TODO implement
    private char symbol;
    public ComputerPlayer(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public int takeTurn(GameBoard board) {
        return 0;
    }

    @Override
    public Character getSymbol() {
        return null;
    }
}
