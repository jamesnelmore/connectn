/**
 * Shows instructions to user for playing game.
 */
package edu.wm.cs.cs301.connectn.view.dialogs;

import edu.wm.cs.cs301.connectn.view.ConnectNFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class InstructionsDialog extends JDialog {

    private static final long serialVersionUID = 5283895279020555816L;
	private final CancelAction cancelAction;
    private JEditorPane editorPane;

    public InstructionsDialog(ConnectNFrame view) {
        super(view.getFrame(), "Instructions", true);
        this.cancelAction = new CancelAction();

        add(createMainPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(view.getFrame());
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        URL url = InstructionsDialog.class.getResource("/instructions.html");

        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");
        try {
            editorPane.setPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setPreferredSize(new Dimension(600, 480));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * 
     * @return JPanel containing "done" button to close the window.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
        ActionMap actionMap = panel.getActionMap();
        actionMap.put("cancelAction", cancelAction);

        JButton button = new JButton("Done");
        button.addActionListener(cancelAction);
        panel.add(button);

        return panel;
    }

    /**
     * Action used to close the dialog.
     */
    private class CancelAction extends AbstractAction {

        private static final long serialVersionUID = 1908595140095812249L;

		@Override
        public void actionPerformed(ActionEvent event) {
            dispose();
        }

    }

}
