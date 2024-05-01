package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.ConnectN;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.view.dialogs.AboutDialog;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {
    private final ConnectNFrame view;

    public Menu(ConnectNFrame view) {
        super();
        this.view = view;

        this.add(createHelpMenu());
        this.add(createDifficultyMenu());
        this.add(createSpacer());
        this.add(createExitButton());
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");

        JMenuItem instructionsItem = new JMenuItem("Instructions...");
        instructionsItem.addActionListener(event -> view.showInstructions());
        helpMenu.add(instructionsItem);

        JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.addActionListener(event -> new AboutDialog(view));
        helpMenu.add(aboutItem);

        return helpMenu;
    }

    private JMenu createDifficultyMenu() {
        JMenu difficultyMenu = new JMenu("Difficulty");

        for (GameMode mode : GameMode.values()) {
            JMenuItem item = new JMenuItem(mode.name);
            item.addActionListener(event -> {
                System.out.println("Setting game mode: " + mode.name);
                view.getFrame().dispose();
                ConnectN.playGame(mode);
            });
            difficultyMenu.add(item);
        }

        return difficultyMenu;
    }

    private JMenuItem createExitButton() {
        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(event -> System.exit(0));
        return exitButton;
    }

    private JLabel createSpacer() {
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(600, 20)); // Set preferred size for spacer
        spacer.setEnabled(false);
        return spacer;
    }
}
