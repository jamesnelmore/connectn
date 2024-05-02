/**
 * Panel of buttons the user uses to select a column to play in.
 */
package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.controller.GameButtonAction;
import edu.wm.cs.cs301.connectn.model.ConnectNGame;

import javax.swing.*;
import java.awt.*;

public class GameButtonPanel {
    private final ConnectNGame game;
    private final ConnectNFrame frame;

    private final JButton[] buttons;
    private final JPanel panel;

    GameButtonPanel(ConnectNFrame frame) {
        this.game = frame.getModel();
        this.buttons = new JButton[game.getColumnCount()];
        this.frame = frame;
        this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 5));

        for (int i = 0; i < game.getColumnCount(); i++) {
            JButton button = createButton(i);
            buttons[i] = button;
            panel.add(button);
        }
    }

    private JButton createButton(int index) {
        JButton button = new JButton(String.valueOf(index+1));
        button.setActionCommand(String.valueOf(index));

        button.setPreferredSize(new Dimension(64, 30));
        // TODO set width
        // TODO set height

        button.addActionListener(new GameButtonAction(this.frame, this.game));
        return button;
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
