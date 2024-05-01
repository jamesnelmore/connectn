package edu.wm.cs.cs301.connectn.view.dialogs;

import edu.wm.cs.cs301.connectn.ConnectN;
import edu.wm.cs.cs301.connectn.model.GameBoard;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.view.AppFont;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

import javax.swing.*;
import java.awt.*;

public class PlayAgainDialog extends JDialog {
    private final ConnectNFrame oldFrame;

    public PlayAgainDialog(ConnectNFrame oldFrame, GameBoard.MoveResult MoveResult) {
        super(oldFrame.getFrame(), "Play Again", true);
        this.oldFrame = oldFrame;

        add(createMainPanel(), BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(oldFrame.getFrame());

        setVisible(true);

    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        panel.add(createMessagePanel(), BorderLayout.NORTH);
        panel.add(createButtonPanel(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMessagePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        JLabel label = new JLabel("You Win!");
        label.setFont(AppFont.TITLE.font);
        panel.add(label);

        return panel;
    }

    private JPanel createButtonPanel() {
       JPanel buttonPanel = new JPanel(new FlowLayout());

       JButton exitButton = new JButton("Exit");
       exitButton.addActionListener(event -> System.exit(0));
       buttonPanel.add(exitButton);

       JButton playAgainButton = new JButton("Play Again");

       playAgainButton.addActionListener(event -> playAgain());
       buttonPanel.add(playAgainButton);

       return buttonPanel;
    }

    private void playAgain() {
        // dispose of old frame
//        make new game
//        close dialog

        GameMode gameMode = this.oldFrame.getModel().getGameMode();
        ConnectN.playGame(gameMode);

        oldFrame.getFrame().dispose();
        this.dispose();


    }
}
