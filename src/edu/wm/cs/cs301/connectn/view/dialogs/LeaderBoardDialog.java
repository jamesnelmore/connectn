/**
 * Dialog showing the user past high scores. Is updated when the user sets a new high score.
 */
package edu.wm.cs.cs301.connectn.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import edu.wm.cs.cs301.connectn.model.LeaderBoard;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;


public class LeaderBoardDialog extends JDialog {
	private static final long serialVersionUID = 638681900507655747L;
	private final MainPanel mainPanel;
	public LeaderBoardDialog(ConnectNFrame view) {
		super();
		this.setTitle("Leaderboard");
		mainPanel = new MainPanel();
        
        add(mainPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
	}
	
	private class MainPanel extends JPanel {
		private static final long serialVersionUID = 7400100091428159227L;

		MainPanel() {
			this.setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(300, 100));
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			String[] columnNames = {"Difficulty", "Name", "Turns"};
	        LeaderBoard l = LeaderBoard.getLeaderBoard();
	        String[] names = l.getNames();
	        int[] scores = l.getHighScores();
	        Object[][] data = {
	            {"Small",  ifNull(names[0]), ifZero(scores[0])},
	            {"Medium", ifNull(names[1]), ifZero(scores[1])},
	            {"Large",  ifNull(names[2]), ifZero(scores[2])},
	        };

	        JTable table = new JTable(data, columnNames){
	            private static final long serialVersionUID = 5719950460227039719L;

				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	                }
	            };
	        
	        this.add(table, BorderLayout.CENTER);
	        
	        JLabel title = new JLabel("Leaderboard");
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setFont(new Font("Dialog", Font.BOLD, 20));
	        
	        this.add(title, BorderLayout.NORTH);
			
	        System.out.println("Repainted");
		}
	}

	private String ifNull(String baseString) {
		return (baseString != "") ? baseString : "No high score";
	}
	
	private String ifZero(int baseInt) {
		return (baseInt != 0) ? String.valueOf(baseInt) : "-";
	}
}
