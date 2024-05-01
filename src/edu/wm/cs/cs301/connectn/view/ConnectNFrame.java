package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.ConnectN;
import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.view.dialogs.AboutDialog;
import edu.wm.cs.cs301.connectn.view.dialogs.InstructionsDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ConnectNFrame {
    private final ConnectNGame model;

    private final JFrame frame;
    private final GameBoardPanel gameBoardPanel;
    private final GameButtonPanel gameButtonPanel;

    public ConnectNFrame(ConnectNGame model) {
        this.model= model;
        this.gameBoardPanel = new GameBoardPanel(this, model, 700); //todo width
        this.gameButtonPanel = new GameButtonPanel(this);

        this.frame = createAndShowGUI();

    }
    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("ConnectN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new Menu(this));
        frame.setResizable(false);

        frame.add(gameBoardPanel, BorderLayout.CENTER);
        frame.add(gameButtonPanel.getPanel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getGameBoardPanel() {
        return gameBoardPanel;
    }

    public ConnectNGame getModel() {
        return this.model;
    }

    public void repaintPanel() {
        gameBoardPanel.repaint();
    }

    public void showInstructions() {
        new InstructionsDialog(this);
    }
}

