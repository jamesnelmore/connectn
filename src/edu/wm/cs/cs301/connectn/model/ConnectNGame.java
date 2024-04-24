/**
 * Represents a game of Connect-N. Controls the flow of play and requests turns from each each player,
 * and passes turns to the board for processing.
 */
package edu.wm.cs.cs301.connectn.model;

import edu.wm.cs.cs301.connectn.model.GameBoard.MoveResult;
import edu.wm.cs.cs301.connectn.model.players.Player;

import static edu.wm.cs.cs301.connectn.model.GameBoard.MoveResult.*;

public class ConnectNGame {
    private Player[] players;
    private GameBoard board;
    private GameMode gameMode;

    public ConnectNGame(Player player1, Player player2, GameMode gameMode) {
        this.players = new Player[]{player1, player2};
        this.gameMode = gameMode;
        this.board = new GameBoard(this.gameMode.rows, this.gameMode.columns, this.gameMode.n);
    }

//    public void playGame() {
//        this.board = new GameBoard(this.gameMode.rows, this.gameMode.columns, this.gameMode.n);
//
//        while (true) {
//            for (Player player : players) {
//                int playersMove = player.takeTurn(this.board);
//                MoveResult moveResult = this.board.applyTurn(playersMove, player.getSymbol());
//                board.displayBoard();
//
////				If worked, do nothing
////				If invalid move, print message that turn was forfeited and move on
////				If game won, return winner
//
//                switch (moveResult) {
//                    case MOVEAPPLIED:
//                        break;
//                    case INVALIDMOVE:
//                        System.out.println("Player " + player.getSymbol() + " made invalid move: "+(playersMove + 1)+". Turn forfeited.");
//                        break;
//                    case GAMEWON:
//                        System.out.println("Player " + player.getSymbol() + " wins in " + this.board.getTurn() + " turns.");
////                        LeaderBoard.getLeaderBoard().updateScore(gameMode, board.getTurn());
////                        LeaderBoard.getLeaderBoard().displayBoard();
////                        todo turn on leaderboard
//                        return;
//                    case TIE:
//                        System.out.println("Game tied.");
//                        return;
//                }
//            }
//        }
//    }

    public int getColumnCount() {
        // todo
        return gameMode.columns;
    }

    public int getMaximumRows() {
        //todo
        return gameMode.rows;
    }

    public Location[][] getLocationGrid() {
        return board.board;
    }
}
