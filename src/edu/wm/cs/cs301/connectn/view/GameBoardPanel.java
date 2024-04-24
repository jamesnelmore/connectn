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

    private void drawOutline(Graphics2D g2d, Rectangle r) {
        int x = r.x + 1;
        int y = r.y + 1;
        int width = r.width - 2;
        int height = r.height - 2;
        Color outlineColor = new Color(211, 214, 218);
        g2d.setColor(outlineColor);
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawLine(x, y, x + width, y);
        g2d.drawLine(x, y + height, x + width, y + height);
        g2d.drawLine(x, y, x, y + height);
        g2d.drawLine(x + width, y, x + width, y + height);
    }

    private void drawCell(Graphics2D g2d,
                          Location location, Rectangle r, Font titleFont) {
        if (location != null) {
//            g2d.setColor(wordleResponse.getBackgroundColor());
            g2d.fillRect(r.x, r.y, r.width, r.height);
//            g2d.setColor(wordleResponse.getForegroundColor());
            drawCenteredString(g2d,
                    Character.toString(location.getToken()), r, titleFont);
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
}
