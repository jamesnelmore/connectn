/**
 * Client interface for the ConnectNGame class.
 * Players compete against each other in ConnectNGames.
 */
package edu.wm.cs.cs301.connectn.model.players;

import edu.wm.cs.cs301.connectn.model.GameBoard;

public interface Player {
    public int takeTurn(GameBoard board);
    public Character getSymbol();
}
