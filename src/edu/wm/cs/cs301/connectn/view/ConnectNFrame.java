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
        JFrame frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
        frame.setResizable(false);

        frame.add(gameBoardPanel, BorderLayout.CENTER);
        frame.add(gameButtonPanel.getPanel(), BorderLayout.SOUTH);

        frame.pack(); // todo what does this do
        frame.setLocationByPlatform(true); // todo what does this do
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    private JMenuBar createMenuBar() { // todo break out into own class
        JMenuBar menuBar = new JMenuBar();

//    Menu item Help

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem instructionsItem = new JMenuItem("Instructions...");
        instructionsItem.addActionListener(event -> showInstructions());
        helpMenu.add(instructionsItem);

        JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.addActionListener(event -> new AboutDialog(this));
        helpMenu.add(aboutItem);

//    Menu item Difficulty

        JMenu difficultyMenu = new JMenu("Difficulty");
        menuBar.add(difficultyMenu);

        for (GameMode mode : GameMode.values()) {
            JMenuItem item = new JMenuItem(mode.name);
            item.addActionListener(event -> {
                System.out.println("Setting game mode: " + mode.name);
//    		Close current game
                getFrame().dispose();
                ConnectN.playGame(mode);
//    		shutdown();
//    		Start new game
            });
            difficultyMenu.add(item);
        }

        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(event -> System.exit(0));
        menuBar.add(exitButton);
        return menuBar;
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

