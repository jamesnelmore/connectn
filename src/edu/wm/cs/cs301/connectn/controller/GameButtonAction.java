package edu.wm.cs.cs301.connectn.controller;

import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.GameBoard;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameButtonAction extends AbstractAction {
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
    }
    private void handleHumanMoveResult(GameBoard.MoveResult moveResult) {
        switch (moveResult) {
            case INVALIDMOVE: // in theory not possible
                throw new IllegalStateException("Invalid move");
            case GAMEWON:
                humanWins();
                break;
            case TIE:
                playersTie();
                break;
            case MOVEAPPLIED:
//                todo play for computer
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
                humanLoses();
                break;
            case TIE:
                playersTie();
            case MOVEAPPLIED:
                break; // no action needed, but case statements over enums should be exhaustive
            default:
                throw new IllegalStateException("Unexpected value: " + computerMoveResult);
        }
    }

    private void humanWins() {
        // TODO fill out
        System.out.println("Human Wins!");
    }

    private void humanLoses() {
        // TODO fill out
        System.out.println("Human Loses :(");
    }

    private void playersTie() {
        // TODO fill out
        System.out.println("Players tie");
    }
    }
