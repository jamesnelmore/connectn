package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.ConnectN;
import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.view.dialogs.AboutDialog;
import edu.wm.cs.cs301.connectn.view.dialogs.InstructionsDialog;

import javax.swing.*;


public class ConnectNFrame {
    private final JFrame frame;
    private final ConnectNGame model;

    public ConnectNFrame(ConnectNGame model) {
        this.model= model;
        this.frame = createAndShowGUI();
    }
    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("ConnectN");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
        frame.setResizable(false);
// todo shutdown when X clicked

        frame.pack(); // todo what does this do
        frame.setLocationByPlatform(true); //todo what does this do
        frame.setVisible(true);
        return frame;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

//    Menu item Help

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem instructionsItem = new JMenuItem("Instructions...");
        instructionsItem.addActionListener(event -> new InstructionsDialog(this));
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
        return menuBar;
    }

//     Getters and Setters

    JFrame getFrame() {
        return frame;
    }
}

