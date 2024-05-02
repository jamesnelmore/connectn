/**
 * Panel showing the game board. Creates and manages a grid of rectanges, filling them in to represent the attached ConnectNGame.
 */
package edu.wm.cs.cs301.connectn.view;

import edu.wm.cs.cs301.connectn.model.ConnectNGame;
import edu.wm.cs.cs301.connectn.model.Location;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GameBoardPanel extends JPanel {
    private final int topMargin, leftMargin, cellWidth;

    private final Insets insets;

    private final Rectangle[][] grid;

    private final ConnectNGame model;

    @Serial
    private static final long serialVersionUID = 368734339387680409L;
    
    public GameBoardPanel(ConnectNFrame view, ConnectNGame model, int width) {
        this.model = model;
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
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font titleFont = AppFont.TITLE.font;
        Location[][] locationGrid = model.getLocationGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Rectangle r = grid[row][column];
                Location location = locationGrid[row][column];
                drawOutline(g2d, r);
                drawCell(g2d, location, r, titleFont);
            }
        }
    }

    /**
     * Creates rectangles of the appropriate size to represent grid squares of the board.
     * @return array of rectangles in shape of the game board.
     */
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

    /**
     * Draws outline around the provided rectangle
     * @param g2d Graphics2D object used to interact with user space
     * @param rect rectangle to outline
     */
    private void drawOutline(Graphics2D g2d, Rectangle rect) {
        int x = rect.x + 1;
        int y = rect.y + 1;
        int width = rect.width - 2;
        int height = rect.height - 2;
        Color outlineColor = new Color(211, 214, 218);
        g2d.setColor(outlineColor);
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawLine(x, y, x + width, y);
        g2d.drawLine(x, y + height, x + width, y + height);
        g2d.drawLine(x, y, x, y + height);
        g2d.drawLine(x + width, y, x + width, y + height);
    }

    /**
     * Draws a player's symbol in the rectangle.
     * @param g2d Graphics2D object used to interact with user space
     * @param location location object represented by rect
     * @param rect rectangle the user will see
     * @param titleFont font to draw the symbol in
     */
    private void drawCell(Graphics2D g2d,
                          Location location, Rectangle rect, Font titleFont) {
        if (location != null) {
            g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
            g2d.setColor(Color.BLACK);
            drawCenteredString(g2d,
                    Character.toString(location.getToken()), rect, titleFont);
        }
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g2d  The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect,
                                    Font font) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
                + metrics.getAscent();

        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }

    public int getCellWidth() {
        return cellWidth;
    }
}
