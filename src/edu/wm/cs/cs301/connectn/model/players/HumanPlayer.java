/**
 * The user's player in a ConnectNGame. Stores user's symbol.
 * Does not make moves for player, but left to provide way for AI to assist with move selection.
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
        return 0;
    }

    @Override
    public Character getSymbol() {
        return this.symbol;
    }

}
