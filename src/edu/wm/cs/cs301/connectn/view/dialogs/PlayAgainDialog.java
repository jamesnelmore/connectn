/**
 * Appears at end of game. Informs the user of win/lose/tie, and new high score.
 * If appropriate, allows user to save high score to the leaderboard.
 * Allows user to play another game or exit application.
 */
package edu.wm.cs.cs301.connectn.view.dialogs;

import edu.wm.cs.cs301.connectn.ConnectN;
import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.model.LeaderBoard;
import edu.wm.cs.cs301.connectn.view.AppFont;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PlayAgainDialog extends JDialog {
    private static final long serialVersionUID = -7551063657412615662L;
	private final ConnectNFrame oldFrame;
    private final ConnectNGame model;
    ConnectNGame.GameResult gameResult;

    /**
     * 
     * @param oldFrame the ConnectNFrame of the completed game.
     * @param gameResult Whether or not the player won, lost, or tied.
     * @param newHighScore Whether or not the user set a high score.
     */
    public PlayAgainDialog(ConnectNFrame oldFrame, ConnectNGame.GameResult gameResult, boolean newHighScore) {
        super(oldFrame.getFrame(), "Play Again", false);
        this.oldFrame = oldFrame;
        this.gameResult = gameResult;
        this.model = oldFrame.getModel();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        this.add(createMessagePanel());
        this.add(createMoveText());
        
        if (newHighScore) {
        	this.add(createHighScoreEntryPanel());
        }
        
        this.add(createButtonPanel());
        
        pack();
        setLocationRelativeTo(oldFrame.getFrame());

        setVisible(true);

    }
    /**
     * Creates message informing player of win/loss/tie.
     * @return JPanel with message.
     */
    private JPanel createMessagePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        JLabel label = new JLabel(getMessage());
        label.setFont(AppFont.TITLE.font);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        return panel;
    }
    
    /**
     * 
     * @return JLabel with the numbers of turns of the game.
     */
    private JLabel createMoveText() {
    	int turns = model.getTurn();
    	JLabel text = new JLabel("In " + turns + " Moves");
    	
    	return text;
    }

    /**
     * 
     * @return JPanel with a text field for the user to enter their name for the leaderboard.
     */
    private JPanel createHighScoreEntryPanel() {
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
    	JLabel label = new JLabel("New High Score! Enter name and press enter:");
    	label.setHorizontalAlignment(JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    	panel.add(label);
    	
    	JTextField nameBox = new JTextField(15);
    	nameBox.setHorizontalAlignment(JLabel.CENTER);
        nameBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        nameBox.addActionListener(e -> {
        	LeaderBoard.getLeaderBoard()
        	.updateScore(model.getGameMode(), model.getTurn(), nameBox.getText());
        });
        
        panel.add(nameBox);
    	
    	
    	return panel;
    }
    
    /**
     * 
     * @return JPanel of exit and play again buttons.
     */
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
        GameMode gameMode = this.oldFrame.getModel().getGameMode();
        ConnectN.playGame(gameMode);

        oldFrame.getFrame().dispose();
        this.dispose();
    }

    private String getMessage() {
        return switch (gameResult) {
            case WIN -> "You Win!";
            case LOSE -> "Computer Wins :(";
            case TIE -> "Tie!";
        };
    }
}