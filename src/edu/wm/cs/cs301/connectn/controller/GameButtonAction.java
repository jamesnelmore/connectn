/**
 * The main controller of the program. Updates model when a column is selected.
 */
package edu.wm.cs.cs301.connectn.controller;

import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.ConnectNGame.GameResult;
import edu.wm.cs.cs301.connectn.model.GameBoard;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.model.LeaderBoard;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;
import edu.wm.cs.cs301.connectn.view.dialogs.PlayAgainDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameButtonAction extends AbstractAction {
    private static final long serialVersionUID = -8673720832690380128L;
	private final ConnectNFrame view;
    private final ConnectNGame game;
    
    /**
     * @param view The ConnectNFrame to listen for.
     * 
     * @param game The model to update.
     */
    public GameButtonAction(ConnectNFrame view, ConnectNGame game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int move = Integer.parseInt(e.getActionCommand());
        System.out.println("Heard button " + move);
        GameBoard.MoveResult moveResult = game.playHumanTurn(move);

        handleHumanMoveResult(moveResult);
        view.repaintPanel();
    }

    /**
     * Checks for win conditions and queries Computer for turn.
     * 
     * @param moveResult The result of the user's turn.
     */
    private void handleHumanMoveResult(GameBoard.MoveResult moveResult) {
        switch (moveResult) {
            case INVALIDMOVE: // in theory not possible
                throw new IllegalStateException("Invalid move");
            case GAMEWON:
                handleGameEnd(ConnectNGame.GameResult.WIN);
                break;
            case TIE:
                handleGameEnd(ConnectNGame.GameResult.TIE);
                break;
            case MOVEAPPLIED:
                GameBoard.MoveResult computerMoveResult = this.game.playComputerTurn();
                handleComputerMoveResult(computerMoveResult);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + moveResult);
        }
    }

    /**
     * Checks for win conditions after computer's turn.
     * @param computerMoveResult result of the computer's move.
     */
    private void handleComputerMoveResult(GameBoard.MoveResult computerMoveResult) {

        switch (computerMoveResult) {
            case INVALIDMOVE:
                throw new RuntimeException("Computer made illegal move");
            case GAMEWON:
                handleGameEnd(ConnectNGame.GameResult.LOSE);
                break;
            case TIE:
                handleGameEnd(ConnectNGame.GameResult.TIE);
            case MOVEAPPLIED:
                break; // no action needed, but case statements over enums should be exhaustive
            default:
                throw new IllegalStateException("Unexpected value: " + computerMoveResult);
        }
    }

    /**
     * Handles Leader board and PlayAgainDialog logic at end of game
     * @param gameResult Final game status of game.
     */
    private void handleGameEnd(ConnectNGame.GameResult gameResult) {
    	GameMode gameMode = game.getGameMode();
    	int turns = game.getTurn();
    	int oldHighScore = LeaderBoard.getLeaderBoard().getHighScore(gameMode);
    	
    	Boolean showHighScore = (gameResult == GameResult.WIN)  && (turns < oldHighScore || oldHighScore == 0);
    	
        new PlayAgainDialog(view, gameResult, showHighScore);
        
    }
    
}
