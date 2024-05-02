package edu.wm.cs.cs301.connectn.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import edu.wm.cs.cs301.connectn.model.LeaderBoard;
import edu.wm.cs.cs301.connectn.view.AppFont;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;


public class LeaderBoardDialog extends JDialog {
	
	public LeaderBoardDialog(ConnectNFrame view) {
		super(view.getFrame(), "Instructions", true);
        

        add(createMainPanel(), BorderLayout.CENTER);
        

        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
	}
	

	private JPanel createMainPanel() {
		// Define data for the table.
        String[] columnNames = {"Difficulty", "Name", "Turns"};
        LeaderBoard l = LeaderBoard.getLeaderBoard();
        String[] names = l.getNames();
        int[] scores = l.getHighScores();
        Object[][] data = {
            {"Small",  names[0], scores[0]},
            {"Medium", names[1], scores[1]},
            {"Large",  names[2], scores[2]},
        };

        // Create a table with the data and column names.
        JTable table = new JTable(data, columnNames);
       

        // Create a panel and add the table to it.
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        mainPanel.add(table, BorderLayout.CENTER);
        
        JLabel title = new JLabel("Instructions");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        
        mainPanel.add(title, BorderLayout.NORTH);
		
		return mainPanel;
	}
	
	public void update() {
		add(createMainPanel(), BorderLayout.CENTER);
	}
}
