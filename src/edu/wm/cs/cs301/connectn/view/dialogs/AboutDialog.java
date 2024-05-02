/**
 * Displays information about the application.
 */
package edu.wm.cs.cs301.connectn.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;

import edu.wm.cs.cs301.connectn.view.AppFont;
import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

public class AboutDialog extends JDialog {
    private static final long serialVersionUID = 1856329864973204328L;

	public AboutDialog(ConnectNFrame view) {
    	this.setLayout(new BorderLayout());
    	
    	JLabel title = new JLabel("ConnectN");
    	title.setFont(AppFont.TITLE.font);
    	
        this.add(new JLabel("ConnectN."), BorderLayout.NORTH);
        this.add(new JLabel("Created 2024 by James Elmore."), BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(250, 70));
        this.pack();
        this.setVisible(true);
    }
}
