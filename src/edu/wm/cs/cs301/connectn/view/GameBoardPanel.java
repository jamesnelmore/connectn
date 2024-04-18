package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.model.ConnectNGame;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GameBoardPanel extends JPanel {
    private final int topMargin, leftMargin, cellWidth;

    private final Insets insets;

    private final Rectangle[][] grid;

    private final ConnectNGame model;

    private final ConnectNFrame view;

    @Serial
    private static final long serialVersionUID = 368734339387680409L;
    public GameBoardPanel(ConnectNFrame view, ConnectNGame model, int width) {
        this.model = model;
        this.view = view;
        this.topMargin = 0;
        this.cellWidth = 64;
        this.insets = new Insets(0, 6, 6, 6);

        int wordWidth = (cellWidth + insets.right) * model.getColumnCount();
        this.leftMargin = (width - wordWidth) / 2;
        int height = (cellWidth + insets.bottom) * model.getMaximumRows()
                + 2 * topMargin;
        this.setPreferredSize(new Dimension(width, height));

        this.grid = calculateRectangles();
    }

    private Rectangle[][] calculateRectangles() {
        Rectangle[][] grid = new Rectangle[model.getMaximumRows()][model
                .getColumnCount()];

        int x = leftMargin;
        int y = topMargin;

        for (int row = 0; row < model.getMaximumRows(); row++) {
            for (int column = 0; column < model.getColumnCount(); column++) {
                grid[row][column] = new Rectangle(x, y, cellWidth,
                        cellWidth);
                x += cellWidth + insets.right;
            }
            x = leftMargin;
            y += cellWidth + insets.bottom;
        }

        return grid;
    }
}
