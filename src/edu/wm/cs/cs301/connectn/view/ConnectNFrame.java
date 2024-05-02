package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.ConnectN;
import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.GameMode;
import edu.wm.cs.cs301.connectn.view.dialogs.AboutDialog;
import edu.wm.cs.cs301.connectn.view.dialogs.InstructionsDialog;
import edu.wm.cs.cs301.connectn.view.dialogs.LeaderBoardDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ConnectNFrame {
    private final ConnectNGame model;

    private final JFrame frame;
    private final GameBoardPanel gameBoardPanel;
    private final GameButtonPanel gameButtonPanel;
    private final MoveCounterPanel moveCounterPanel;
    private LeaderBoardDialog leaderBoardDialog;

    public ConnectNFrame(ConnectNGame model) {
        this.model= model;
        this.gameBoardPanel = new GameBoardPanel(this, model, 700);
        this.gameButtonPanel = new GameButtonPanel(this);
        this.moveCounterPanel = new MoveCounterPanel();

        this.frame = createAndShowGUI();
        
        this.leaderBoardDialog = new LeaderBoardDialog(this);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                leaderBoardDialog.dispose();
            }
        });

    }
    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("ConnectN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new Menu(this));
        frame.setResizable(false);
        
        frame.add(moveCounterPanel, BorderLayout.NORTH);
        frame.add(gameBoardPanel, BorderLayout.CENTER);
        frame.add(gameButtonPanel.getPanel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }
    
    private class MoveCounterPanel extends JPanel {
    	private static final long serialVersionUID = 1696508320122726885L;
		private int turns;
    	
    	public MoveCounterPanel() {
    	this.setPreferredSize(new Dimension(30, 40));
    	turns = 0;
    	}
    	
    	@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            Dimension panelSize = this.getSize();
            int x = panelSize.width / 2;
            int y = panelSize.height / 2;
            
            g.setFont(AppFont.TEXT.font);
            g.drawString("Turn " + turns, x, y);
        }
    	
    	public void incrementCounter() {
    		turns += 1;
    		this.repaint();
    	}
    	
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
    
    public LeaderBoardDialog getLeaderBoardDialog() {
    	return this.leaderBoardDialog;
    }

    public void repaintPanel() {
        gameBoardPanel.repaint();
        moveCounterPanel.incrementCounter();
    }

    public void showInstructions() {
        new InstructionsDialog(this);
    }
}

