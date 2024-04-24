package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.controller.GameButtonAction;

import javax.swing.*;

public class GameButtonPanel {
    private final JButton[] buttons;
    private final JPanel panel;
    GameButtonPanel(int numberButtons) {
        this.buttons = new JButton[numberButtons];
        this.panel = new JPanel();

        for (int i = 0; i < numberButtons; i++) {
            JButton button = new JButton(String.valueOf(i+1));
            // TODO add action listener
            buttons[i]= button;
            panel.add(button);
        }
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
