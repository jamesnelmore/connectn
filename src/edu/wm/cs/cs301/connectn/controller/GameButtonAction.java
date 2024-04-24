package edu.wm.cs.cs301.connectn.controller;

import edu.wm.cs.cs301.connectn.model.ConnectNGame;
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
        System.out.println("Heard button " + e.getActionCommand());
    }
}
