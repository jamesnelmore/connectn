/**
 * Represents a game of Connect-N. Controls the flow of play and requests turns from each each player,
 * and passes turns to the board for processing.
 */
package edu.wm.cs.cs301.connectn.model;

import edu.wm.cs.cs301.connectn.model.GameBoard.MoveResult;
import edu.wm.cs.cs301.connectn.model.players.ComputerPlayer;
import edu.wm.cs.cs301.connectn.model.players.HumanPlayer;
import edu.wm.cs.cs301.connectn.model.players.Player;

import static edu.wm.cs.cs301.connectn.model.GameBoard.MoveResult.*;

public class ConnectNGame {
    private final HumanPlayer humanPlayer;
    private final ComputerPlayer computerPlayer;
    private final GameBoard board;
    private final GameMode gameMode;

    public ConnectNGame(HumanPlayer humanPlayer, ComputerPlayer computerPlayer, GameMode gameMode) {
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.gameMode = gameMode;
        this.board = new GameBoard(this.gameMode.rows, this.gameMode.columns, this.gameMode.n);
    }

    public MoveResult playHumanTurn(int move) {
        return this.board.applyTurn(move, humanPlayer.getSymbol());
    }

    public MoveResult playComputerTurn() {
        int move = this.computerPlayer.takeTurn(this.board);
        return this.board.applyTurn(move, computerPlayer.getSymbol());
    }

    public int getColumnCount() {
        // todo
        return gameMode.columns;
    }

    public int getMaximumRows() {
        //todo
        return gameMode.rows;
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public Location[][] getLocationGrid() {
        return board.board;
    }
}
