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
    public GameButtonAction(ConnectNFrame view, ConnectNGame game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int move = Integer.parseInt(e.getActionCommand());
        System.out.println("Heard button " + move);
        // todo ensure player can't take 2 turns in a row really quickly
        GameBoard.MoveResult moveResult = game.playHumanTurn(move);

        handleHumanMoveResult(moveResult);
        view.repaintPanel();
    }

    private void handleHumanMoveResult(GameBoard.MoveResult moveResult) {
        ConnectNGame.GameResult gameResult;
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

    private void handleGameEnd(ConnectNGame.GameResult gameResult) {
    	GameMode gameMode = game.getGameMode();
    	int turns = game.getTurn();
    	int oldHighScore = LeaderBoard.getLeaderBoard().getHighScore(gameMode);
    	
    	Boolean showHighScore = (gameResult == GameResult.WIN)  && (turns < oldHighScore);
    	
        PlayAgainDialog playAgainDialog = new PlayAgainDialog(view, gameResult, true); // TODO  should be showHighScore
        
    }
    }
