package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.controller.GameButtonAction;
import edu.wm.cs.cs301.connectn.model.ConnectNGame;

import javax.swing.*;

public class GameButtonPanel {
    private final ConnectNGame game;
    private final ConnectNFrame frame;

    private final JButton[] buttons;
    private final JPanel panel;
    GameButtonPanel(ConnectNFrame frame) {
        this.game = frame.getModel();
        this.buttons = new JButton[game.getColumnCount()];
        this.frame = frame;
        this.panel = new JPanel();

        for (int i = 0; i < game.getColumnCount(); i++) {
            JButton button = createButton(i);
            // TODO add action listener
            buttons[i]= button;
            panel.add(button);
        }
    }

    private JButton createButton(int index) {
        JButton button = new JButton(String.valueOf(index+1));
        button.setActionCommand(String.valueOf(index));
        // TODO set width
        // TODO set height

        button.addActionListener(new GameButtonAction(this.frame, this.game));
        return button;
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
