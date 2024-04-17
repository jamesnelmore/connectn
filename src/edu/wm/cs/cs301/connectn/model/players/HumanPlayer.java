/**
 * The user's player in a ConnectNGame. Takes input from the user and validates it.
 * Does not allow user to make an invalid move.
 */
package edu.wm.cs.cs301.connectn.model.players;

import edu.wm.cs.cs301.connectn.model.GameBoard;
public class HumanPlayer implements Player {
    private Character symbol;

    public HumanPlayer(Character symbol) {
        this.symbol = symbol;
    }

    @Override
    public int takeTurn(GameBoard board) {
        // TODO
        return 0;
    }

    @Override
    public Character getSymbol() {
        return this.symbol;
    }

}
